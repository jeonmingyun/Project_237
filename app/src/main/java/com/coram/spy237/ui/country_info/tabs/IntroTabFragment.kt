package com.coram.spy237.ui.country_info.tabs

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.coram.spy237.R
import com.coram.spy237.databinding.FragmentIntroTabBinding

class IntroTabFragment : Fragment() {
    // view binding
    private var mBinding: FragmentIntroTabBinding? = null
    private val binding get() = mBinding!!

    private var bundleCountryName = "마다가스카르"

    companion object {
        const val BUNDLE_KEY_COUNTRY_NAME = "BUNDLE_KEY_COUNTRY_NAME"

        fun newInstance(countryName: String): IntroTabFragment {
            val args = Bundle()

            args.putString(BUNDLE_KEY_COUNTRY_NAME, countryName)
            val fragment = IntroTabFragment()
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
        mBinding = FragmentIntroTabBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setBundleData()
        initIntroTab()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        // view binding
        mBinding = null
    }

    // TEST_DATA 국가 > 국가상세 > INTRO 탭
    val madaIntroData = arrayListOf<Any>(
        R.drawable.mada_intro_img,
        "• 마다가스카르는 동아프리카 해안선에서 약 400km 떨어진 인도양에 위치한 섬나라로, 섬으로 구성된 국가 중 그 면적이 세계에서 두 번째로 크다.\n\n" +
                "• 다양한 환경의 생육지가 존재하며, 전 세계 생명종의 5%가 서식하고 있는 생태계의 보고이다.\n\n" +
                "• 기원전에도 이 섬에 인간이 존재했다는 증거가 있지만 A.D. 350-550년 오늘날의 인도네시아로부터 대규모 정착이 시작됐고, A.D. 1000년 경 아프리카에서 이민자들이 도착했다.\n\n" +
                "• 16세기-19세기 후반 원주민 메리나 왕국이 지배했으나, 1896년 프랑스에 정복되어 식민지가 되었다가 1960년 독립했다.",
        R.drawable.mada_map
    )
    val ukIntroData = arrayListOf<Any>(
        R.drawable.uk_intro_img,
        "• 동유럽 국가에 속하는 우크라이나는 러시아, 폴란드 등 7개의 국가와 흑해, 아조프해와 접하고 있다.\n\n" +
                "• 동유럽 평원과 이어져 있으며 기후는 비교적 온화 한 편이다.\n\n" +
                "• 지하 자원이 풍부한 우크라이나는 경지율이 약 70%에 이르고 있어 구소련 시절 매우 중요한 지위를 차지하기도 하였다.\n\n" +
                "• 우크라이나는 구 소련 내 성경 벨트로, 독립전까지 교회는 많은 핍박과 고난을 당하였다.\n\n" +
                "• 소련의 공산주의는 30년 전 무너졌지만, 공산주의의 영향이 아직까지 깊게 남아 있고, 시장경제로 전환하면서 많은 사람을 가난해지며 일부 소수층의 지갑만 채워줬다.",
        R.drawable.uk_map
    )
    val philIntroData = arrayListOf<Any>(
        R.drawable.phil_intro_img,
        "• 필리핀은 7,107개의 섬으로 이뤄진 군도 국가이며 동남아시아에 있다. 북부의 루손, 중부의 비사야, 남부의 민다나오 크게 3개 지역으로 분류된다. 스페인과 미국의 식민 지배를 거쳐 1946년 7월 4일에 독립했다. \n\n" +
                " • 두 번의 식민 지배의 영향으로 인구의 80%가 가톨릭 신자인 가톨릭 국가이며 영어와 타갈로그어가 공용어로 사용되고 있다. 환태평양 조산대에 위치하여 화산, 지진, 태풍 등의 자연재해가 빈번하지만 다양한 동식물과 해양생물이 있다. 또한 치안 문제와 높은 범죄율, 부정부패, 빈곤과 실업이 만연하여 빈부의 격차가 심각하다.",
        R.drawable.phil_map
    )
    val usaIntroData = arrayListOf<Any>(
        R.drawable.usa_intro_img,
        "• 명실상부 세계 유일의 초강대국. 미국은 정치, 경제, 사회, 국방 등 국가를 구성하는 대부분에서 1위를 기록한다. \n\n" +
                "• 특히 압도적인 경제력을 자랑하는데, 전 세계 GDP의 25%를 미국이 차지한다. \n\n" +
                "• 이러한 경제력을 바탕으로 미국은 천문학적인 금액을 국방력에 투자한다. \n\n" +
                "• 미국을 '천조국(千兆國)'으로도 부르는 이유는 한 해 국방 예산을 천조 원 가량 쓰기 때문이라고 한다. \n\n" +
                "• 그러나 화려한 이면에 숨겨진 실상은 마약으로 고통 받는 나라라는 오명이다. \n\n" +
                "• 2021년 미국 젊은 층의 사망 원인 1위가 마약성 진통제 오남용으로 꼽힐 만큼 현재 심각한 마약 문제를 겪고 있다. ",
        R.drawable.usa_map
    )

    // TEST_DATA 국가 > 국가상세 > INTRO 탭
    private fun initIntroTab() {
        when(bundleCountryName) {
            "마다가스카르" -> {
                setIntroItem(madaIntroData)
            }
            "우크라이나" -> {
                setIntroItem(ukIntroData)
            }
            "필리핀" -> {
                setIntroItem(philIntroData)
            }
            "미국" -> {
                setIntroItem(usaIntroData)
            }
            else -> {
                setIntroItem(madaIntroData)
            }
        }
    }

    private fun setIntroItem(dataList : ArrayList<Any>) {
        binding.introImg.setImageResource(dataList[0].toString().toInt())
        binding.introContent.text = dataList[1].toString()
        binding.capitalImg.setImageResource(dataList[2].toString().toInt())
    }
}