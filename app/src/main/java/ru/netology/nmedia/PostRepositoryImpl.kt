package ru.netology.nmedia

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

class PostRepositoryImpl : PostRepository {
    private var postIt = 1
    private var posts = listOf(
            Post(postIt++, 999, false, 200, 5700,
                    "Нетология. Университет интернет-профессий",
                    "Команда Нетологии помогает освоить новую профессию и навыки из любой точки мира. Это даёт человеку возможность жить более счастливой и наполненной жизнью: заниматься любимым делом, путешествовать, проводить время с семьёй и в целом — гармонично развиваться. Большинство наших студентов проходят через эту важную трансформацию и становятся успешными. Для нас это показатель, что мы двигаемся в правильном направлении.  netology.ru",
                    "25 мая в 18:20"
            ),
            Post(postIt++, 99, false, 20000, 500,
                    "Нетология. Университет интернет-профессий",
                    "Команда Нетологии помогает освоить новую профессию и навыки из любой точки мира. Это даёт человеку возможность жить более счастливой и наполненной жизнью: заниматься любимым делом, путешествовать, проводить время с семьёй и в целом — гармонично развиваться. Большинство наших студентов проходят через эту важную трансформацию и становятся успешными. Для нас это показатель, что мы двигаемся в правильном направлении.  netology.ru",
                    "25 мая в 18:20"
            ),
            Post(postIt++, 99, false, 2000000, 5700,
                    "Нетология. Университет интернет-профессий",
                    "Команда Нетологии помогает освоить новую профессию и навыки из любой точки мира. Это даёт человеку возможность жить более счастливой и наполненной жизнью: заниматься любимым делом, путешествовать, проводить время с семьёй и в целом — гармонично развиваться. Большинство наших студентов проходят через эту важную трансформацию и становятся успешными. Для нас это показатель, что мы двигаемся в правильном направлении.  netology.ru",
                    "25 мая в 18:20"
            ),
            Post(postIt++, 9, false, 2000000, 5700,
                    "Нетология. Университет интернет-профессий",
                    "Команда Нетологии помогает освоить новую профессию и навыки из любой точки мира. Это даёт человеку возможность жить более счастливой и наполненной жизнью: заниматься любимым делом, путешествовать, проводить время с семьёй и в целом — гармонично развиваться. Большинство наших студентов проходят через эту важную трансформацию и становятся успешными. Для нас это показатель, что мы двигаемся в правильном направлении.  netology.ru",
                    "25 мая в 18:20"
            ),
            Post(postIt++, 999, false, 2000000, 5700,
                    "Нетология. Университет интернет-профессий",
                    "Команда Нетологии помогает освоить новую профессию и навыки из любой точки мира. Это даёт человеку возможность жить более счастливой и наполненной жизнью: заниматься любимым делом, путешествовать, проводить время с семьёй и в целом — гармонично развиваться. Большинство наших студентов проходят через эту важную трансформацию и становятся успешными. Для нас это показатель, что мы двигаемся в правильном направлении.  netology.ru",
                    "25 мая в 18:20"
            ),
            Post(postIt++, 8, false, 7, 9700,
                    "Нетология. Университет интернет-профессий",
                    "Команда Нетологии помогает освоить новую профессию и навыки из любой точки мира. Это даёт человеку возможность жить более счастливой и наполненной жизнью: заниматься любимым делом, путешествовать, проводить время с семьёй и в целом — гармонично развиваться. Большинство наших студентов проходят через эту важную трансформацию и становятся успешными. Для нас это показатель, что мы двигаемся в правильном направлении.  netology.ru",
                    "25 мая в 18:20"
            ),
            Post(postIt++, 100, false, 1000, 5700,
                    "Нетология. Университет интернет-профессий",
                    "Команда Нетологии помогает освоить новую профессию и навыки из любой точки мира. Это даёт человеку возможность жить более счастливой и наполненной жизнью: заниматься любимым делом, путешествовать, проводить время с семьёй и в целом — гармонично развиваться. Большинство наших студентов проходят через эту важную трансформацию и становятся успешными. Для нас это показатель, что мы двигаемся в правильном направлении.  netology.ru",
                    "25 мая в 18:20"
            )
    )

    private var editablePost: Post = Post(0, 0, false, 0, 0, "new post",
            "", "now")

    private val data = MutableLiveData<List<Post>>(posts)

    override fun getAll(): LiveData<List<Post>> = data

    override fun likePost(id: Int) {
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
    }

    override fun sharePost(id: Int) {
        posts = posts.map {
            if (it.id == id) {
                it.copy(shareCount = it.shareCount.inc())
            } else it
        }
        data.value = posts
    }

    override fun removePost(id: Int) {
        posts = posts.filterNot { it.id == id }
        data.value = posts
    }

    override fun newPost(text: String) {
        if (editablePost.id == 0){
            posts = listOf(editablePost.copy(id= postIt++, body = text)) + posts

        } else {
            posts = posts.map {if(it.id == editablePost.id) it.copy(body = text) else it }
            editablePost.id = 0
        }

        data.value = posts
    }



    override fun editPost(post: Post) {
        editablePost = post
    }

    override fun cancelEditing() {
        editablePost.id = 0
    }
}

