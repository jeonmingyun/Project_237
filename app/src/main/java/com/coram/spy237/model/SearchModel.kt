package com.coram.spy237.model

data class SearchModel(val imageUri: String, val searchText: String) {
    companion object {
        // TEST_DATA 검색 > 국가 빠른 검색
        val countryList = arrayListOf(
            SearchModel("", "마다가스카르"),
            SearchModel("", "미국"),
            SearchModel("", "우크라이나"),
            SearchModel("", "필리핀"),

            SearchModel("", "인도네시아"),
            SearchModel("", "방글라데시"),
            SearchModel("", "파키스탄"),
            SearchModel("", "제트남"),
        )
        // TEST_DATA 검색 > 선교사 빠른 검색
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