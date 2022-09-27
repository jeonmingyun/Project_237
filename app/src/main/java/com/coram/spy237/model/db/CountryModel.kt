package com.coram.spy237.model.db

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class CountryModel(
    val id: Int = -1,
    val name: String = "",
    val prayContent: String = "",
    val continent: String = "",
    val peopleCount: String = "",
    val prayCount: String = "",
    val flagUrl: String = ""
) : Parcelable {
    fun getTestList(): ArrayList<CountryModel> {
        return arrayListOf(
            CountryModel(0, "마다가스카르", "복음주의 교회의 성장으로 필리핀 현장에 인구성장률보다 그리스도인의 수가 빠르게 늘게 하심에 감사합니다. 이들이 영적으로 강력한 복음 공동체로 전역에서 전도 제자 운동을 일으키고 오직 복음만 전하는 교회가 확산되게 하옵소서. 4만 3000개의 지역 중 2만 3000개 지역에 교회가 없습니다. 복음에 생을 건 전도자들이 무교회 지역에 교회를 개척하는  비전을 품게 하옵소서."),
            CountryModel(0, "미국", "하나님 아버지 하나님의 절대계획 속에 있는 오늘, 하나님 자녀된 신분에 집중하길 원합니다! 예수가 나의 그리스도 되시고 성령이 함께하시고 인도하시기에 염려하지 않고 하나님을 전적으로 신뢰하는 믿음으로 살아가게 하옵소서. 오늘 나에게 주신 말씀에 집중할 때 주시는 하나님의 절대계획을 깨닫고 나의 모든 여정이 살리는 여정 속에 있게 하옵소서 예수 그리스도 이름으로 기도합니다"),
            CountryModel(0, "필리핀", "예수 그리스도를 믿음으로 말미암아 완전한 구원 얻게 하심에 감사를 드립니다. 오늘도 나를 향한 하나님의 절대계획울 말씀 속에서 확인 하게 하옵소서. 이를 위해 오늘도 하나님 자녀된 신분에 집중하길 소원합니다. 성삼위 하나님이 지금도 나와 함께 하시고 나를 인도하시고 보호하심을 믿습니다! 이때, 내게 예수가 그리스도되신다는 믿음이 완존 회복되는 영적 상태를 허락하옵소서. 그리스도 안에 모든 것 다 있음을 고백함 속에 오늘도 달려가게 하옵소서"),
        )
    }
}