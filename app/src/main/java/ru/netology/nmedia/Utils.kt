package ru.netology.nmedia

fun digitToString(input: Int):String{
    val values = arrayOf(
        arrayOf(4,1,'K'),
        arrayOf(5,2,'K'),
        arrayOf(6,3,'K'),
        arrayOf(7,1,'M'),
        arrayOf(8,2,'M'),
        arrayOf(9,3,'M'))
    val intstr = input.toString()
    var suffix = ""
    for (i in values.indices){
        if (intstr.length == values[i][0]){
            if (((intstr.length == 4) || (intstr.length == 7)) && (intstr.substring(1,2).toInt() != 0)){
                suffix = ".${intstr.substring(1,2)}"
            }
            return "${intstr.substring(0,(values[i][1] as Int))}$suffix${values[i][2]}"
        }
    }
    return input.toString()
}