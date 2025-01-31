package com.gilpereda.videomanager.ui.components

import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Remove
import androidx.compose.material3.FilledTonalIconButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.gilpereda.videomanager.ui.common.borders
import com.gilpereda.videomanager.ui.common.leftBorder
import com.gilpereda.videomanager.ui.common.topBorder

class TableViewModel<Item>(
    val tableName: String,
    val headers: List<String>,
    val data: List<Item>,
    val columnWeights: List<Float>,
    val itemToRow: Item.() -> List<String> = { listOf(this.toString()) },
)

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun <Item> EditableTable(
    viewModel: TableViewModel<Item>,
    modifier: Modifier = Modifier,
    onAddItem: () -> Unit = {},
    onRemoveItem: (Item) -> Unit = {},
    onEditItem: (Item) -> Unit = {},
    borderStroke: BorderStroke = BorderStroke(1.dp, MaterialTheme.colorScheme.outlineVariant),
) {
    val strokeWidth = borderStroke.width
    val strokeColor = (borderStroke.brush as? SolidColor)?.value ?: MaterialTheme.colorScheme.outlineVariant
    var selectedRow: Item? by remember { mutableStateOf(null) }
    Column(modifier = modifier.border(BorderStroke(1.dp, MaterialTheme.colorScheme.outlineVariant))) {
        Row(
            modifier = Modifier.fillMaxWidth().padding(start = 8.dp),
        ) {
            FilledTonalIconButton(
                onClick = onAddItem,
                modifier =
                    Modifier.size(30.dp),
                shape = MaterialTheme.shapes.small,
                colors = IconButtonDefaults.filledTonalIconButtonColors().copy(containerColor = MaterialTheme.colorScheme.surface),
            ) {
                Icon(Icons.Default.Add, "Add element to ${viewModel.tableName}")
            }
            FilledTonalIconButton(
                onClick = { selectedRow?.let { onRemoveItem(it) } },
                modifier = Modifier.size(30.dp),
                shape = MaterialTheme.shapes.small,
                colors = IconButtonDefaults.filledTonalIconButtonColors().copy(containerColor = MaterialTheme.colorScheme.surface),
            ) {
                Icon(Icons.Default.Remove, "Remove element from ${viewModel.tableName}")
            }
        }
        Row(
            modifier =
                Modifier.borders(strokeWidth, strokeColor, bottom = false),
        ) {
            viewModel.headers.forEachIndexed { index, header ->
                Box(
                    Modifier
                        .leftBorder(strokeWidth, strokeColor)
                        .weight(viewModel.columnWeights.getOrElse(index) { 1f }),
                ) {
                    Text(
                        text = header,
                        style = MaterialTheme.typography.bodyMedium.copy(fontWeight = FontWeight.Bold),
                        modifier =
                            Modifier.padding(8.dp),
                    )
                }
            }
        }
        if (viewModel.data.isNotEmpty()) {
            viewModel.data.forEach { row ->
                Box(
                    Modifier
                        .fillMaxWidth()
                        .combinedClickable(
                            onClick = { selectedRow = if (selectedRow == row) null else row },
                            onDoubleClick = { onEditItem(row) },
                        ).background(
                            if (selectedRow == row) {
                                MaterialTheme.colorScheme.surfaceContainerHigh
                            } else {
                                MaterialTheme.colorScheme.surface
                            },
                        ),
                ) {
                    Row(
                        modifier =
                            Modifier
                                .topBorder(strokeWidth, strokeColor),
                    ) {
                        viewModel.itemToRow(row).forEachIndexed { index, cell ->
                            Box(
                                Modifier
                                    .leftBorder(strokeWidth, strokeColor)
                                    .weight(viewModel.columnWeights.getOrElse(index) { 1f }),
                            ) {
                                Text(
                                    text = cell,
                                    modifier =
                                        Modifier
                                            .padding(8.dp),
                                )
                            }
                        }
                    }
                }
            }
        } else {
            Row(
                modifier =
                    Modifier
                        .topBorder(strokeWidth, strokeColor),
            ) {
                Box(
                    modifier =
                        Modifier
                            .fillMaxWidth()
                            .padding(8.dp),
                ) {
                    Text("No elements found. To add a new element click on the '+' button.")
                }
            }
        }
    }
}

@Preview
@Composable
fun EditableTablePreview() {
    Surface {
        Box(Modifier.fillMaxSize().padding(12.dp)) {
            EditableTable(
                viewModel =
                    TableViewModel(
                        tableName = "Table",
                        headers = listOf("Header 1", "Header 2", "Header 3"),
                        data =
                            listOf<List<String>>(
                                listOf("1", "2", "3"),
                                listOf("4", "5", "6"),
                                listOf("7", "8", "9"),
                            ),
                        columnWeights = listOf(1f, 1f, 1f),
                        itemToRow = { this },
                    ),
            )
        }
    }
}
