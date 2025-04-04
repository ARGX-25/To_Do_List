package my.first.to_dolist.data

import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlin.random.Random

data class ToDo(
//    val id: Long = 0L,
    val title: String = "",
    val description: String = "",

)

object DummyTodoList{
    var todoList = listOf(
        ToDo("DSA","Arrays and Strings"),
        ToDo( "Physics Lab", "Writing the experiment for Stefan's Constant "),
        ToDo("Maths", "Surface Areas and Volumes"),
        ToDo("Electrical","Three Phase AC Circuit"),
        ToDo("App Dev","Material3 with Philip Lackner")
    )
}
