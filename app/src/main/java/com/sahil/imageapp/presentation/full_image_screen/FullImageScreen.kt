package com.sahil.imageapp.presentation.full_image_screen

import android.annotation.SuppressLint
import android.widget.Toast
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.gestures.animateZoomBy
import androidx.compose.foundation.gestures.rememberTransformableState
import androidx.compose.foundation.gestures.transformable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImagePainter
import coil.compose.rememberAsyncImagePainter
import com.sahil.imageapp.R
import com.sahil.imageapp.domain.model.UnSplashImage
import com.sahil.imageapp.presentation.components.DownloadOptionsBottomSheet
import com.sahil.imageapp.presentation.components.FullImageViewTopBar
import com.sahil.imageapp.presentation.components.ImageAppLoadingBar
import com.sahil.imageapp.presentation.components.ImageDownloadOption
import com.sahil.imageapp.presentation.util.SnackbarEvent
import com.sahil.imageapp.presentation.util.rememberWindowInsetsController
import com.sahil.imageapp.presentation.util.toggleStatusBar
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import kotlin.math.max
import kotlin.math.min

@SuppressLint("UnusedBoxWithConstraintsScope")
@OptIn(ExperimentalFoundationApi::class, ExperimentalMaterial3Api::class)
@Composable
fun FullImageScreen(
    snackbarHostState : SnackbarHostState,
    snackbarEvent : Flow<SnackbarEvent>,
    image: UnSplashImage?,
    onBackClick: () -> Unit,
    onPhotoGraphNameClick: (String) -> Unit,
    onImageDownloadClick: (String, String?) -> Unit,
) {
    val context = LocalContext.current
    val scope = rememberCoroutineScope()
    var showBars by rememberSaveable { mutableStateOf(false) }
    val windowInsetsController = rememberWindowInsetsController()
    val sheetState = rememberModalBottomSheetState(skipPartiallyExpanded = true)
    var isDownloadBottomSheetOpen by remember { mutableStateOf(false) }

    LaunchedEffect(key1 = Unit) {
        snackbarEvent.collect {event ->
            snackbarHostState.showSnackbar(
                message = event.message,
                duration = event.duration
            )
        }
    }

    LaunchedEffect(key1 = Unit) {
        windowInsetsController.toggleStatusBar(show = showBars)
    }

    BackHandler(enabled = !showBars) {
        windowInsetsController.toggleStatusBar(show = true)
        onBackClick()
    }

    DownloadOptionsBottomSheet(
        isOpen = isDownloadBottomSheetOpen,
        sheetState = sheetState,
        onDismissRequest = { isDownloadBottomSheetOpen = false },
        onOptionClick = { option ->
            scope.launch{ sheetState.hide() }.invokeOnCompletion {
                if (!sheetState.isVisible) isDownloadBottomSheetOpen = false
            }
            val url = when(option) {
                ImageDownloadOption.SMALL -> image?.imageUrlSmall
                ImageDownloadOption.MEDIUM -> image?.imageUrlRegular
                ImageDownloadOption.ORIGINAL -> image?.imageUrlRow
            }
            url?.let {
                onImageDownloadClick(it, image?.description?.take(20))
                Toast.makeText(context,"Downloading...", Toast.LENGTH_SHORT).show()
            }
        }

    )
    Box(
        modifier = Modifier.fillMaxSize(),
    ) {
        BoxWithConstraints(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            var scale by remember { mutableFloatStateOf(1f) }
            var offset by remember { mutableStateOf(Offset.Zero) }
            val isImageZoomed: Boolean by remember { derivedStateOf { scale != 1f } }

            val maxScale = 5f

            val transformState = rememberTransformableState { zoomChange, offSetChange, _ ->
                scale = max(1f, min(scale * zoomChange, maxScale))
                val maxX = (constraints.maxWidth * (scale - 1)) / 2
                val maxY = (constraints.maxHeight * (scale - 1)) / 2
                offset = Offset(
                    x = (offset.x + offSetChange.x).coerceIn(-maxX, maxX),
                    y = (offset.y + offSetChange.y).coerceIn(-maxY, maxY)
                )
            }

            var isLoading by remember { mutableStateOf(true) }
            var isError by remember { mutableStateOf(false) }
            val imageLoader = rememberAsyncImagePainter(
                model = image?.imageUrlRow,
                onState = { imageState ->
                    isLoading = imageState is AsyncImagePainter.State.Loading
                    isError = imageState is AsyncImagePainter.State.Error
                }
            )
            if (isLoading) {
                ImageAppLoadingBar()
            }
            Image(
                painter = if (isError.not()) imageLoader else painterResource(id = R.drawable.error_icon),
                contentDescription = null,
                modifier = Modifier
                    .fillMaxSize()
                    .transformable(transformState)
                    .combinedClickable(
                        onDoubleClick = {
                            if (isImageZoomed) {
                                scale = 1f
                                offset = Offset.Zero
                            } else {
                                scope.launch { transformState.animateZoomBy(zoomFactor = 3f) }
                            }
                        },
                        onClick = {
                            showBars = !showBars
                            windowInsetsController.toggleStatusBar(show = showBars)
                        },
                        indication = null,
                        interactionSource = remember { MutableInteractionSource() }
                    )
                    .graphicsLayer {
                        scaleX = scale
                        scaleY = scale
                        translationX = offset.x
                        translationY = offset.y
                    }
            )
        }
        FullImageViewTopBar(
            modifier = Modifier
                .align(Alignment.TopCenter)
                .fillMaxWidth()
                .padding(horizontal = 5.dp, vertical = 40.dp),
            image = image,
            onBackClick = onBackClick,
            isVisible = showBars,
            onPhotoGraphNameClick = onPhotoGraphNameClick,
            onDownloadImgClick = { isDownloadBottomSheetOpen = true}
        )

    }
}