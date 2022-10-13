package com.coram.spy237.ui.country_info.tabs

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.coram.spy237.databinding.FragmentTravelTabBinding

class TravelTabFragment : Fragment() {
    // view binding
    private var mBinding: FragmentTravelTabBinding? = null
    private val binding get() = mBinding!!

    private var bundleCountryName = "마다가스카르"

    /* TEST_DATA 국가 > 국가상세 > TRAVEL 탭
    * {여행 서브타이틀, 여행 내용글, 비행정보, 통화 정보, 대사관 정보} */
    val madaData = arrayListOf<Any>(
        "희귀동식물의 천국",
        "• 마다가스카르는 모론다바의 바오밥 나무 군락지, 희귀동물을 볼 수 있는 안다시베 국립공원, 세계 3대 옥색 해변으로 유명한 디에고 항구, 유네스코 세계 문화유산 칭기 데 베마하라 등 볼거리가 많은 나라이다.\n\n" +
                "• 내전이 빈번한 아프리카 타국들에 비해 치안이 양호하나, 도시와 여행지에는 생계형 범죄자가 많고 외곽지역에서는 무장 강도도 있기에 가이드를 동행해야 한다.",
        "경유 2회\n최단 비행 24시간\n최대 60일 단기비자",
        "법정통화 : 아리아리(MGA)",
        "주마다가스카르 대한미국대사관"
    )
    val ukData = arrayListOf<Any>(
        "동유럽의 비옥하고 우거진 땅, 우크라이나",
        "• 유럽에서 비교적 큰 영토를 가진 우크라이나는 러시아 서쪽 흑해 연안에 위치하고 있다. \n\n" +
                "• 풍부한 지하자원으로 인해 다양한 광산들이 관광지 각광받고 있으며 무비자로 90일까지 체류 가능하다. \n\n" +
                "• 2022년 10월 인천국제공항(ICN)에서 키예프 국제공항(KBP)까지 최단 시간 여정은 바르샤바, 리가를 경유한 편도 23시간이 소요된다.",
        "경유 2회\n최단 비행 23시간\n최대 90일 무사증",
        "법정통화 : 흐리우냐(UAH)",
        "주우크라이나 대한미국대사관"
    )
    val philData = arrayListOf<Any>(
        "자연재해, 치안 불안",
        "• 필리핀은 화산, 지진, 태풍 등 자연재해가 많이 발생한다. 또한 6월•10월 우기, 11월•3월 건기, 4월•5월은 연중 가장 덥고, 우기에는 수시로 기상 예보를 확인하는 것이 좋다.\n\n" +
                "• 총기 소지가 허용된 나라이며 치안이 불안하고, 홀로 여행은 피하는 것이 좋다.",
        "직항 O\n최단 비행 4시간\n최대 30일 무사증",
        "법정통화 : 페소(PHP)",
        "주필리핀 대한미국대사관"
    )
    val usaData = arrayListOf<Any>(
        "팁은 필수!",
        "• 미국에서의 모든 서비스 이용에는 팁을 지불해야 한다. \n\n" +
                "• 식당과 미용실, 택시 등 이용 시 비용의 15-20% 정도를 팁으로 지불하는 것이 보통이다. \n\n" +
                "• 호텔이나 공항에서 짐을 이동해 주는 경우에는 보통 가방 당 \$1 정도 지불하면 된다.",
        "직항 O\n최단 비행 14시간\n최대 90일 무사증",
        "법정통화 : 미국달러(USD)",
        "주미국 대한미국대사관"
    )

    companion object {
        const val BUNDLE_KEY_COUNTRY_NAME = "BUNDLE_KEY_COUNTRY_NAME"

        fun newInstance(countryName: String): TravelTabFragment {
            val args = Bundle()

            args.putString(BUNDLE_KEY_COUNTRY_NAME, countryName)
            val fragment = TravelTabFragment()
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
        mBinding = FragmentTravelTabBinding.inflate(layoutInflater)
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

    // TEST_DATA 국가 > 국가상세 > TRAVEL 탭
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
        binding.subTitle.text = dataList[0].toString() // 여행 서브타이틀
        binding.travelContent.text = dataList[1].toString() // 여행 내용글
        binding.flightInfo.text = dataList[2].toString() // 비행 정보
        binding.moneyInfo.text = dataList[3].toString() // 통화 정보
        binding.embassyInfo.text = dataList[4].toString() // 대사관 정보
    }
}