package my.first.to_dolist

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.api.Context
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.withContext
import my.first.to_dolist.data.ToDo
import kotlin.random.Random

class todoViewModel:ViewModel() {

    //Save function to store date in firebase firestore database
//    private val todoCollectionRef = Firebase.firestore.collection("ToDo")
//
//    fun saveTodo(todoItem: ToDo) = viewModelScope.launch(Dispatchers.IO) {
//        try{
//            todoCollectionRef.add(todoItem).await()
//        }catch(e:Exception){
//            withContext(Dispatchers.Main){
//                Log.e("TodoViewModel", "Error saving todo: ${e.message}", e)
//
//            }
//        }
//    }




    var todoTitleState by mutableStateOf("")
    var todoDescriptionState by mutableStateOf("")

    // Saving the To-Do list items in form of a ToDo object
//    var todoItem = ToDo(
//        Random.nextLong(),
//        todoTitleState,
//        todoDescriptionState
//    )


    fun onTodoTitleChanged( newString: String){
        todoTitleState = newString

    }

    fun onTodoDescriptionChanged( newString: String) {
        todoDescriptionState = newString
    }


}