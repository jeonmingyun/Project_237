package com.coram.spy237.ui.country_info.tabs

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.coram.spy237.databinding.FragmentPeopleTabBinding

class PeopleTabFragment : Fragment() {
    // view binding
    private var mBinding: FragmentPeopleTabBinding? = null
    private val binding get() = mBinding!!

    private var bundleCountryName = "마다가스카르"

    // TEST_DATA 국가 > 국가상세 > PEOPLE 탭
    // {언어, 문해율, 언어 내용글, 인구수, 인구순위, 민족수, 인구 내용글}
    val madaData = arrayListOf<Any>(
        "영어",
        "76.7%",
        "• 지리상 반투어를 사용하는 아프리카 남부와 가깝지만 동남아시아 어군에 속하는 말라가시어를 사용한다. 부족별로 다양한 방언이 있지만 서로 소통이 가능하며, 문자는 로마자로 표기한다.\n\n" +
                "• 수도 등 대도시에서는 프랑스어를 한다면 의사소통에 문제가 없지만 현지어인 말라가시를 쓸 경우 상대방이 더욱 우호적이고 친밀하게 반응한다.",
        "28M",
        "-",
        "-",
        "• 인구의 90% 이상을 말라가시족으로 분류하나, 여러 부족을 통칭하는 이름이다. 그 중 가장 큰 부족은 전체 인구의 ¼ 정도 되는 메리나족으로 인도네시아 혈통이 강하게 드러나며, 주로 내륙고원에 거주한다."
    )
    val ukData = arrayListOf<Any>(
        "우크라라이나어",
        "99.8%",
        "• 국제 조사에 따른 모어 분포를 보면 67.5%가 ‘법적’공용어인 키릴 문자를 기반으로 한 우크라이나어를, 29.6%가 ‘실제적’공식어인 러시아어를 쓴다. \n" +
                "• 이 외에도 루마니아어, 폴란드어, 헝가리어, 벨라루스어, 체코어 등 소수 민족어도 쓰인다.\n\n" +
                "• 러시아어는 하르키우와 도네츠크, 루한스크 등의 동부와 오데사 등의 남부, 크림 반도 전역에서 주로 쓰이며 동부와 남부의 지역 공식어로도 지정되어 있다.\n\n" +
                "• 거의 대부분의 우크라이나인들이 우크라이나어와 러시아어를 모두 이해한다.\n\n",
        "43M",
        "-",
        "4",
        "• 우크라이나의 민족 구성 비율은 우크라이나인 77.8%, 러시아인 17.3%이다. 약 12,000명의 고려인이 거주하며, 벨라루스인 역시 거주한다."
    )
    val philData = arrayListOf<Any>(
        "타갈로그어, 영어",
        "96.3%",
        "",
        "114M",
        "13",
        "-",
        "• 총면적 343,488㎢의 필리핀의 총 인구수는 2022년 기준 112,508,991명이며 세계에서 13번째로 인구가 많은 나라이다.\n\n" +
                "• 스페인과 미국의 두 번의 식민지배와 이민자들의 영향으로 미국, 스페인, 중국계 혼혈이 많으며 필리핀 정치와 경제는 혼혈인들이 장악하고 있다."
    )
    val usaData = arrayListOf<Any>(
        "-",
        "-",
        "",
        "337M",
        "3",
        "-",
        "• 미국의 인구는 약 3억 3천만 명으로 전 세계 인구 수 3위를 차지한다. \n\n" +
                "• 미국의 전체 인구를 인종 별로 구분해보면, 2020년 미국 인구조사국에서 실시한 조사 결과 백인이 57.8%로 가장 높고 이어 히스패닉이 18.7%, 흑인 12.4%, 그리고 아시아계가 6%를 차지하고 있다.\n\n" +
                "• 흥미로운 사실은 미국의 인구는 갈수록 증가하는 추세이지만 백인이 차지하는 비중은 갈수록 줄어들고 있다는 것이다. \n\n" +
                "• 미국 인구 증가의 핵심은 히스패닉으로 미국 전체 인구증가의 절반을 차지했다. 또한 아시아인의 비중도 증가 추세이다."
    )

    companion object {
        const val BUNDLE_KEY_COUNTRY_NAME = "BUNDLE_KEY_COUNTRY_NAME"

        fun newInstance(countryName: String): PeopleTabFragment {
            val args = Bundle()

            args.putString(BUNDLE_KEY_COUNTRY_NAME, countryName)
            val fragment = PeopleTabFragment()
            fragment.arguments = args
            return fragment
        }
    }

    private fun setBundleData() {
        bundleCountryName = arguments?.getString(BUNDLE_KEY_COUNTRY_NAME).toString()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // view binding
        mBinding = FragmentPeopleTabBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setBundleData()
        initLayout()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        // view binding
        mBinding = null
    }

    // TEST_DATA 국가 > 국가상세 > PEOPLE 탭
    private fun initLayout() {
        when (bundleCountryName) {
            "마다가스카르" -> {
                setLayoutItem(madaData)
            }
            "우크라이나" -> {
                setLayoutItem(ukData)
            }
            "필리핀" -> {
                setLayoutItem(philData)
            }
            "미국" -> {
                setLayoutItem(usaData)
            }
            else -> {
                setLayoutItem(madaData)
            }
        }
    }

    private fun setLayoutItem(dataList: ArrayList<Any>) {
        binding.textView3.text = dataList[0].toString() // 언어
        binding.textView4.text = dataList[1].toString() // 문해율
        binding.langContent.text = dataList[2].toString() // 언어 내용글
        binding.textView31.text = dataList[3].toString() // 인구수
        binding.textView41.text = dataList[4].toString() // 인구순위
        binding.textView411.text = dataList[5].toString() // 민족수
        binding.populationContent.text = dataList[6].toString() // 인구 내용글
    }
}