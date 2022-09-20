package com.coram.spy237.model

import com.coram.spy237.R

data class HeaderViewpagerModel(
    val name: String = "",
    val title: String = "",
    val uri: Int = -1,
    val isChecked: Boolean = false
) {
    fun getTestList(): ArrayList<HeaderViewpagerModel> {
        return arrayListOf(
            HeaderViewpagerModel("마다가스카르", "세계에서 4번째로 큰 섬\n그리고 사역자", R.drawable.country_img01, false),
            HeaderViewpagerModel("우크라이나", "우크라이나를 위한\n기도", R.drawable.country_img02, false),
            HeaderViewpagerModel("미국", "차별 금지법 또 다른 논쟁", R.drawable.country_img03, false)
        )
    }
}