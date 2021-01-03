package com.example.impliment.utils

interface ItemTouchHelperAdaptr {
fun onItemMove(fromPosition:Int,toPosition:Int)
fun onItemDismiss(position:Int)
}