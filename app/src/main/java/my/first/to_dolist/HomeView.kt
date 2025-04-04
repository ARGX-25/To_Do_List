package my.first.to_dolist

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Scaffold
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.Card
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import my.first.to_dolist.data.DummyTodoList
import my.first.to_dolist.data.ToDo

@Composable
fun HomeView(
    navController: NavController,
    viewModel: todoViewModel
){

    val context = LocalContext.current
    Scaffold(
        topBar = {AppBarView(title = "To-Do List")},
        floatingActionButton = {
            FloatingActionButton(
                modifier= Modifier.padding(all = 20.dp),
                contentColor = colorResource(id = R.color.app_bar_color),
                backgroundColor = Color.Black,
                onClick = {

                    Toast.makeText(context, "Button Clicked", Toast.LENGTH_SHORT).show()
                    navController.navigate(Screen.addScreen.route)

                }) {
                Icon(imageVector = Icons.Default.Add, contentDescription =null,
                    tint = colorResource(id = R.color.white))
            }
        }
    ){
        LazyColumn(modifier = Modifier
            .fillMaxSize()
            .padding(it)) {
            items(// Here goes the item Database in our case the firestore
                DummyTodoList.todoList
            ){
                todo ->
                     SwipeToDeleteContainer(
                         item = todo,
                        onDelete = {
                            // Here we will add the delete functionality
                            DummyTodoList.todoList -= todo
                         }) {todo ->todoListItem(todo = todo){}
                         Text(
                             text = todo.title,
                             modifier = Modifier
                                 .fillMaxWidth()
                                 .background(MaterialTheme.colors.background)
                                 .padding(16.dp)
                         )
                     }

            }
        }
    }
}

@Composable
fun todoListItem(todo : ToDo, onClick: () -> Unit){
    Card(modifier = Modifier
        .fillMaxWidth()
        .padding(top = 8.dp, start = 8.dp, end = 8.dp)
        .clickable {
            onClick()

        },
        elevation = 10.dp,
        backgroundColor = Color.White
    ) {
        Column(modifier = Modifier.padding(16.dp)){
            Text(text = todo.title, fontWeight = FontWeight.ExtraBold)
            Text(text = todo.description)
        }
    }
}