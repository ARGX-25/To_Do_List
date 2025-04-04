package my.first.to_dolist

import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Check
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel


@Composable
fun AppBarView(
    title: String,
    onBackNavClicked: () -> Unit = {},


){

    val navigationIcon: @Composable (() -> Unit)? =
        {   //Our Back Button
            if(!title.contains("To-Do List")){
                IconButton(onClick = { onBackNavClicked() }) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                        tint = Color.White,
                        contentDescription = null
                    )
                }
            }else{
                null
            }

        }
    
    TopAppBar(
        //Our Title Bar

        title = { Text(text = title,
        color = colorResource(id = R.color.white),
        modifier = Modifier
            .padding(start = 2.dp)
            .heightIn(max = 24.dp)) },


        actions = {
            val viewModel: todoViewModel = viewModel()
            if(!title.contains("To-Do List")){
                IconButton(onClick = {
                    // Implementing the save function
//                    viewModel.saveTodo(viewModel.todoItem)
                }) {

                    Icon(Icons.Filled.Check,
                        tint = Color.White,
                        contentDescription = "Save")
                }
            }

        },
        elevation = 3.dp,
        backgroundColor = colorResource(id = R.color.app_bar_color),
        navigationIcon = navigationIcon



        )
}