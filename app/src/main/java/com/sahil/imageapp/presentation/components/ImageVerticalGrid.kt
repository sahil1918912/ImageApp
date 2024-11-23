package com.sahil.imageapp.presentation.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectDragGesturesAfterLongPress
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.lazy.staggeredgrid.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.unit.dp
import com.sahil.imageapp.domain.model.UnSplashImage


@Composable
fun ImageVerticalGrid(
    modifier: Modifier = Modifier,
    images: List<UnSplashImage>,
    onImageClick: (String) -> Unit,
    onImageDragStart: (UnSplashImage) -> Unit,
    onImageDragEnd: () -> Unit
) {
    LazyVerticalStaggeredGrid(
        modifier = modifier,
        columns = StaggeredGridCells.Adaptive(120.dp),
        contentPadding = PaddingValues(10.dp),
        verticalItemSpacing = 10.dp,
        horizontalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        items(images) { image ->
            ImageCard(
                image = image,
                modifier = Modifier
                    .clickable { image?.id?.let { onImageClick(it) } }
                    .pointerInput(Unit){
                        detectDragGesturesAfterLongPress(
                            onDragStart = {onImageDragStart(image)},
                            onDragCancel = {onImageDragEnd()},
                            onDragEnd = {onImageDragEnd()},
                            onDrag = {_,_->}
                        )
                    }
            )
        }
    }
}