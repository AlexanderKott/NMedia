package ru.netology.nmedia

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

class PostRepositoryImpl : PostRepository {

    private val post = Post(999, false, 2000000, 5700,
        "Нетология. Университет интернет-профессий",
        "Команда Нетологии помогает освоить новую профессию и навыки из любой точки мира. Это даёт человеку возможность жить более счастливой и наполненной жизнью: заниматься любимым делом, путешествовать, проводить время с семьёй и в целом — гармонично развиваться. Большинство наших студентов проходят через эту важную трансформацию и становятся успешными. Для нас это показатель, что мы двигаемся в правильном направлении.  netology.ru",
        "25 мая в 18:20"
        )

    private val data = MutableLiveData<Post>(post)

    override fun get(): LiveData<Post> = data

    override fun like() {
       val oldValue = requireNotNull(data.value)
       val likesCalc = if (oldValue.like) { oldValue.likesCount.dec() } else oldValue.likesCount.inc()
       val result = post.copy( like = !oldValue.like, likesCount = likesCalc)
        data.value = result
     }

    override fun share() {
        val oldValue = requireNotNull(data.value)
        val result = post.copy(shareCount = oldValue.shareCount.inc())
        data.value = result
    }
}

