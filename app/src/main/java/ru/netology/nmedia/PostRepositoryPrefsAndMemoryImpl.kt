package ru.netology.nmedia

import android.content.Context
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import ru.netology.nmedia.data.Post

class PostRepositoryPrefsAndMemoryImpl(val context: Context) : PostRepository {
    private var postIt = 1L

    private var posts = listOf<Post>()

    private val etalone = Post(
        0, 0, false, 0, 0, "new post",
        "", "now",
        "eX2qFMC8cFo"
    )

    private var editablePost: Post = etalone


    private val data = MutableLiveData<List<Post>>(posts)

    init {
        checkIsThisFirstRun()
    }

    fun checkIsThisFirstRun() {
        val pref = context.getSharedPreferences("Settings", Context.MODE_PRIVATE)
            .getString("firstTime", "true")
        Log.e("abc= ",pref)
        if (pref == null || pref == "true") {
            prePopulate()
            //save to disc
            saveDataToDisc(sampleData())
        }
        loadDataFromDisc()
    }


    fun prePopulate() {
        val prefs = context.getSharedPreferences("Settings", Context.MODE_PRIVATE)
        prefs.edit().apply {
            putString("firstTime", "false")
            commit()
        }

    }



    fun loadDataFromDisc() {
        val type = TypeToken.getParameterized(List::class.java, Post::class.java).type
        val file = context.filesDir.resolve("json.storage")
        if (file.exists()) {
            context.openFileInput("json.storage").bufferedReader().use {
                posts = Gson().fromJson(it, type)
                data.value = posts

            }
         //   postIt = (posts.maxOfOrNull { it.id.toInt()   } ?: 1)
        }


    }

    fun saveDataToDisc(posts: List<Post>) {
        context.openFileOutput("json.storage", Context.MODE_PRIVATE).bufferedWriter().use {
            it.write(Gson().toJson(posts))
        }
    }

    override fun getAll(): LiveData<List<Post>> = data

    override fun likePost(id: Long) {
        posts = posts.map {
            if (it.id == id) {
                var likes = if (it.like) {
                    it.likesCount.dec()
                } else {
                    it.likesCount.inc()
                }
                it.copy(like = !it.like, likesCount = likes)
            } else it
        }
        data.value = posts
        saveDataToDisc(posts)
    }

    override fun sharePost(id: Long) {
        posts = posts.map {
            if (it.id == id) {
                it.copy(shareCount = it.shareCount.inc())
            } else it
        }
        data.value = posts
        saveDataToDisc(posts)
    }

    override fun removePost(id: Long) {
        posts = posts.filterNot { it.id == id }
        data.value = posts
        saveDataToDisc(posts)
    }

    override fun changePost(text: String) {
        if (editablePost.id == 0L) {  // add new
            posts = listOf(etalone.copy(id = ++postIt , body = text)) + posts
        } else { //edit post
            posts = posts.map { if (it.id == editablePost.id) it.copy(body = text) else it }
            editablePost.id = 0

        }

        data.value = posts
        saveDataToDisc(posts)


    }


    override fun markPostForEdit(post: Post) {
        editablePost = post
    }

    override fun cancelEditing() {
        editablePost.id = 0
    }

    fun sampleData(): List<Post> {
        return listOf(
            Post(
                postIt++, 999, false, 200, 5700,
                "Нетология. Университет интернет-профессий",
                "Команда Нетологии помогает освоить новую профессию и навыки из любой точки мира. Это даёт человеку возможность жить более счастливой и наполненной жизнью: заниматься любимым делом, путешествовать, проводить время с семьёй и в целом — гармонично развиваться. Большинство наших студентов проходят через эту важную трансформацию и становятся успешными. Для нас это показатель, что мы двигаемся в правильном направлении.  netology.ru",
                "25 мая в 18:20",
                "eX2qFMC8cFo"
            ),
            Post(
                postIt++, 99, false, 20000, 500,
                "Нетология. Университет интернет-профессий",
                "Команда Нетологии помогает освоить новую профессию и навыки из любой точки мира. Это даёт человеку возможность жить более счастливой и наполненной жизнью: заниматься любимым делом, путешествовать, проводить время с семьёй и в целом — гармонично развиваться. Большинство наших студентов проходят через эту важную трансформацию и становятся успешными. Для нас это показатель, что мы двигаемся в правильном направлении.  netology.ru",
                "25 мая в 18:20",
                "eX2qFMC8cFo"
            ),
            Post(
                postIt++, 99, false, 2000000, 5700,
                "Нетология. Университет интернет-профессий",
                "Команда Нетологии помогает освоить новую профессию и навыки из любой точки мира. Это даёт человеку возможность жить более счастливой и наполненной жизнью: заниматься любимым делом, путешествовать, проводить время с семьёй и в целом — гармонично развиваться. Большинство наших студентов проходят через эту важную трансформацию и становятся успешными. Для нас это показатель, что мы двигаемся в правильном направлении.  netology.ru",
                "25 мая в 18:20",
                "eX2qFMC8cFo"
            ),
            Post(
                postIt++, 9, false, 2000000, 5700,
                "Нетология. Университет интернет-профессий",
                "Команда Нетологии помогает освоить новую профессию и навыки из любой точки мира. Это даёт человеку возможность жить более счастливой и наполненной жизнью: заниматься любимым делом, путешествовать, проводить время с семьёй и в целом — гармонично развиваться. Большинство наших студентов проходят через эту важную трансформацию и становятся успешными. Для нас это показатель, что мы двигаемся в правильном направлении.  netology.ru",
                "25 мая в 18:20",
                "eX2qFMC8cFo"
            ),
            Post(
                postIt++, 999, false, 2000000, 5700,
                "Нетология. Университет интернет-профессий",
                "Команда Нетологии помогает освоить новую профессию и навыки из любой точки мира. Это даёт человеку возможность жить более счастливой и наполненной жизнью: заниматься любимым делом, путешествовать, проводить время с семьёй и в целом — гармонично развиваться. Большинство наших студентов проходят через эту важную трансформацию и становятся успешными. Для нас это показатель, что мы двигаемся в правильном направлении.  netology.ru",
                "25 мая в 18:20",
                "eX2qFMC8cFo"
            ),
            Post(
                postIt++, 8, false, 7, 9700,
                "Нетология. Университет интернет-профессий",
                "Команда Нетологии помогает освоить новую профессию и навыки из любой точки мира. Это даёт человеку возможность жить более счастливой и наполненной жизнью: заниматься любимым делом, путешествовать, проводить время с семьёй и в целом — гармонично развиваться. Большинство наших студентов проходят через эту важную трансформацию и становятся успешными. Для нас это показатель, что мы двигаемся в правильном направлении.  netology.ru",
                "25 мая в 18:20",
                "eX2qFMC8cFo"
            ),
            Post(
                postIt++, 100, false, 1000, 5700,
                "Нетология. Университет интернет-профессий",
                "Команда Нетологии помогает освоить новую профессию и навыки из любой точки мира. Это даёт человеку возможность жить более счастливой и наполненной жизнью: заниматься любимым делом, путешествовать, проводить время с семьёй и в целом — гармонично развиваться. Большинство наших студентов проходят через эту важную трансформацию и становятся успешными. Для нас это показатель, что мы двигаемся в правильном направлении.  netology.ru",
                "25 мая в 18:20",
                "eX2qFMC8cFo"
            )
        )
    }

}

