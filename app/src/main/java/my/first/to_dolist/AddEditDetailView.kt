@file:OptIn(ExperimentalMaterialApi::class)

package my.first.to_dolist


import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeOut
import androidx.compose.animation.shrinkVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.DismissDirection
import androidx.compose.material.DismissState
import androidx.compose.material.DismissValue
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Scaffold
import androidx.compose.material.SwipeToDismiss
import androidx.compose.material.Text
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.rememberDismissState
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import kotlinx.coroutines.delay


@Composable
fun AddEditDetailView(
    id: Long,
    viewModel: todoViewModel,
    navController: NavController

){
    Scaffold(
        topBar = { AppBarView(title =

        if(id != 0L) stringResource(id = R.string.update_ToDo_List)
        else stringResource(id = R.string.add_ToDo_List)
        ){navController.navigateUp()}}
    ) {
        Column(
            modifier =Modifier.padding(it),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center

        ){
            ToDoTextFeild(label = "Description", value = viewModel.todoDescriptionState,
                onValueChanged = {
                    viewModel.onTodoDescriptionChanged(it)
                })
            Spacer(modifier = Modifier.height(10.dp))

            ToDoTextFeild(label = "Title", value = viewModel.todoTitleState,
                onValueChanged = {
                    viewModel.onTodoTitleChanged(it)
                })

            }








        }
    }



@Composable
fun ToDoTextFeild(
    label: String,
    value: String,
    onValueChanged: (String) -> Unit
){
    OutlinedTextField(value = value,
        onValueChange = onValueChanged,
        label = {Text(text = label, color = Color.Black)},
        modifier = Modifier.fillMaxWidth(),
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
        colors = TextFieldDefaults.outlinedTextFieldColors(
            // Using Default Colors
            textColor = Color.Black,
            // Using Custom Colors
            focusedBorderColor = colorResource(id = R.color.black),
            unfocusedBorderColor = colorResource(id = R.color.black),
            cursorColor = colorResource(id = R.color.black),
            focusedLabelColor = colorResource(id = R.color.black),
            unfocusedLabelColor = colorResource(id = R.color.black)
            )
    )
}
@Composable
fun DeleteBackground(
    swipeDismissState: DismissState
){
    val color = if(swipeDismissState.dismissDirection
        == DismissDirection.EndToStart){
        Color.Red
    }else Color.Transparent

    Box(
        modifier = Modifier
            .fillMaxSize()
            .wrapContentSize(Alignment.CenterEnd)
            .background(color)
            .padding(end = 16.dp),
        contentAlignment = Alignment.CenterEnd
    ){
        Icon(
            imageVector = Icons.Default.Delete,
            contentDescription = "Delete",
            tint = Color.White
        )
    }

}
@Composable
fun <T> SwipeToDeleteContainer(
    item: T,
    onDelete: (T) -> Unit,
    animationDuration: Int = 500,
    content: @Composable (T) -> Unit

){
    var isRemoved by remember { mutableStateOf(false) }
    val state = rememberDismissState(
        confirmStateChange = { value ->
            if(value == DismissValue.DismissedToStart){
                isRemoved = true
                true
            }else{
                false
            }

        }
    )
    LaunchedEffect(key1 = isRemoved){
        if(isRemoved){
            delay(animationDuration.toLong())
            onDelete(item)
        }
    }

    AnimatedVisibility(
        visible = !isRemoved,
        exit = shrinkVertically (
            animationSpec = tween(durationMillis = animationDuration),
            shrinkTowards = Alignment.Top
        ) + fadeOut()
        ){
        SwipeToDismiss(
            state = state,
            background = {
                DeleteBackground(swipeDismissState = state)
            },
            dismissContent = {content(item)},
            directions = setOf(DismissDirection.EndToStart)
        )

    }
}


@Preview
@Composable
fun ToDoTextFeildPreview(){
    ToDoTextFeild(label = "Title", value = "Title", onValueChanged = {})
}