package com.coram.spy237.model

data class SearchModel(val imageUri: String, val searchText: String) {
    companion object {
        val countryList = arrayListOf(
            SearchModel("", "마다가스카르"),
            SearchModel("", "인도네시아"),
            SearchModel("", "방글라데시"),
            SearchModel("", "파키스탄"),
            SearchModel("", "제트남"),
        )
        val nameList = arrayListOf(
            SearchModel("", "김동길"),
            SearchModel("", "김철수"),
            SearchModel("", "나영석"),
            SearchModel("", "전민균"),
            SearchModel("", "김운비"),
            SearchModel("", "신윤수"),
            SearchModel("", "김성재"),
            SearchModel("", "유효진"),
            SearchModel("", "김상아"),
            SearchModel("", "오수려")
        )

        fun getTestCountryList(str: String): List<SearchModel> {
            return countryList.filter { it.searchText.contains(str) }
        }

        fun getTestNameList(str: String): List<SearchModel> {
            return nameList.filter { it.searchText.contains(str) }
        }
    }

}