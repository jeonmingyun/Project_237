package com.coram.spy237.model

data class SearchModel(val imageUri: String, val searchText: String) {
    companion object {
        fun getTestList(str: String): ArrayList<SearchModel> {
            val result: ArrayList<SearchModel> = arrayListOf()

            for(i in 0 until 5) {
                result.add(SearchModel("", str + i.toString()))
            }
            return result
        }
    }

}