package com.coram.spy237.ui.country_info.tabs

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.coram.spy237.R
import com.coram.spy237.databinding.FragmentEvanTabBinding

class EvanTabFragment : Fragment() {
    // view binding
    private var mBinding: FragmentEvanTabBinding? = null
    private val binding get() = mBinding!!

    private var bundleCountryName = "마다가스카르"

    /* TEST_DATA 국가 > 국가상세 > EVAN 탭
    * {
    *   전도 서브타이틀, 전도 내용글, 종교 분포 그래프, 예원 파송/협력선교사
    *   전도협회 파송선교, 세계선교대회 참석자, RTS, 한국 교민 수
    *   유니월드 연계사업, KOICA 중점 협력국, RTS 재학생 여부, 장기체류자,
    *   단기체류자
    * } */
    val madaData = arrayListOf<Any>(
        "개발, 원조, 의료사역 위주 선교 진행 중: 현지 언어와 문화를 익힌 장기 사역자 필요",
        "• 인구의 반이 기독교(개신교 27%, 가톨릭 22%)를 믿는다. 20세기 대형교회 중심으로 일어난 부흥운동으로 다수가 기독교로 개종했으나 기독교인의 90%는 전통신앙에 혼합되어 오직 복음으로 거듭난 사람이 많지 않다.\n\n" +
                "• 섬의 북단과 남단에는 가장 복음화되지 않은 종족이 정령숭배와 이슬람교의 영향 아래 살고 있다.\n\n" +
                "• 외부접근이 어려운 지역이 많아서 항공선교회(MAF)와 헬리미션(Helimission) 등을 통해 사역자와 물품을 보내기도 한다.\n\n" +
                "• 국제 AIM 선교회, 국제 대학생 선교회(CCCI) 등의 큰 단체들이 사역해 왔으며, 주로 토착 단체와 함께 개발과 원조, 직업 훈련, 교육과 보건 등의 사역이 진행되었다.\n\n" +
                "• 인구의 60%가 만 25세 이하로 연령비가 젊은 편이다. 이에 예수전도단(YWAM), 국제 복음주의 학생회(IFES), 어린이 전도협회(CEF) 등에서 소모임이나 캠프와 같은 프로그램으로 사역하고 있기도 하다.\n\n" +
                "• 총 26개 언어 중, 공식어인 말레가시어를 포함한 8개 언어로 성경66권이 번역되어 있다. \n\n" +
                "• 순회선교단의 ‘기도24‧365’는 마다가스카르에 40 종족이 거주하는 것으로 파악하고 있는데, 이 중 11 종족(인구의 1.6%)이 미전도종족이라고 한다.\n\n" +
                "• 현지에는 한국통신, 삼성 등의 기업이 진출해있어 약 200여명의 한국 교민이 거주중이다.",
        R.drawable.mada_graph_religion,
        R.drawable.check_x,
        R.drawable.check_x,
        R.drawable.check_x,
        R.drawable.check_x,
        "한국 교민 수 : 242",
        R.drawable.check_x,
        R.drawable.check_x,
        R.drawable.check_x,
        "장기체류자 : 20",
        "단기체류자 : 7",
    )
    val ukData = arrayListOf<Any>(
        "강력한 기독교 유산을 지닌 땅, 우크라이나",
        "• 우크라이나는 1,000년 전 키예프에서 슬라브족 기독교가 탄생하였다. \n\n" +
                "• 130년 간 수백만 명이 처형된 박해의 시기를 거쳐 살아남은 복음주의 교회는 강인해지고 수적으로도 성장하였다. 종교의 자유가 신장되었으나 아직 헌법과 국가는 신앙의 자유를 지켜주지 못하고 있다.\n\n" +
                "• 우크라이나는 중요한 국가로 동부와 서부 정교회와 가톨릭 사이에 교량 역할을 한다.\n\n" +
                "• 우크라이나는 주로 정교회에 속하지만, 독립교회와 그리스 라틴 가톨릭이 공존한다.\n\n" +
                "• 미신과 껍데기뿐인 신앙에 신자는 감소하고 있지만 영적이며 성경에 관심을 갖는 그리스도인이 점차 많아지고 있다.\n\n" +
                "• 2004년 우크라이나 대통령 선거 때 여당의 부정 선거를 규탄하여 결국 재선거를 치르게 했던 시민 혁명, ‘오렌지 혁명’은 우크라이나의 엄청난 잠재력과 가능성을 보여주었다.\n\n" +
                "• 새로운 변화의 길로 들어서고 있지만 변하지 않은 채 남아 있는 정치 지도자 간의 대립, 러시아의 군사적 위협과 크림 반도 사건으로 안정이 요구되는 상태이다.\n\n" +
                "• 현재도 러시아와의 전쟁 중에 있는 우크라이나는 흩어진 피난민과 계속해서 말씀 운동을 이어 나갈 사역자가 필요하다. \n\n" +
                "• 뿐만 아니라, 20년 간 교회가 꾸준히 성장하면서 지도자를 필요로 하는 천개의 교회에 사역할 지도자를 세우는 훈련, 현지의 교단과 협력한 제자 훈련, 전도 훈련과 신학교 및 전도 학교 훈련의 시스템이 필요한 곳이다.",
        R.drawable.uk_graph_religion,
        R.drawable.check_o,
        R.drawable.check_o,
        R.drawable.check_o,
        R.drawable.check_o,
        "한국 교민 수 : 13,070",
        R.drawable.check_x,
        R.drawable.check_x,
        R.drawable.check_x,
        "장기체류자 : 3.317",
        "단기체류자 : 256"
    )
    val philData = arrayListOf<Any>(
        "인구의 80% 가톨릭, 2만 3,000 지역 무교회, 계속 성장하는 복음주의",
        "• 필리핀은 스페인의 식민지배의 영향으로 인해 인구의 80%가 가톨릭 신자이며, 많은 국민들이 정령숭배와 마술 등의 우상에 깊이 빠져 무속신앙이 잠재의식까지 뿌리내려있다. 그러나 이런 상황에서도 복음주의 교단은 계속해서 성장하고 있다. 필리핀의 복음주의 교회 협의는 68개 교단과 170개 선교단체와 독립교회, 초교파 단체가 모여서 동역하고 있으며 이로 인해 개신교인 수도 빠르게 성장하고 있다.\n\n" +
                "• 개신교인 수가 빠르게 성장하고 있는 반면 다양한 거짓 진리와 이단, 광신적 종교 집단도 많이 있으며 가톨릭과 개신교 교단에서 나온 단체, 비성경적 이교도 집단도 있다. 또한 필리핀 남부 민다나오 섬에는 무슬림 반군과 정부가 50여 년간 분쟁을 지속해왔고 결국 이슬람 자치정부를 허용 받았다. 필리핀에는 복음을 들을 기회조차 없는 무교회 지역이 2만 3,000개나 된다.\n\n" +
                "• 가톨릭과 정령숭배, 우상에 깊이 빠져있고 복음을 들을 기회조차 없는 곳이 너무나 많은 필리핀이었지만 지금은 선교사들의 주요 파송 국가가 되었다. 필리핀에는 1,200명이 넘는 선교사들이 파송 되어있다. \n\n" +
                "• 세계 각지에는 흩어져 있는 필리핀인들이 있고 이들은 문화적 유연성, 어려운 환경에 적응하는 능력, 영어를 비롯한 여러 언어를 구사할 수 있기에 필리핀인 선교사가 되어 사역하는데 매우 유익하여 잠재력이 크다.",
        R.drawable.phil_graph_religion,
        R.drawable.check_o,
        R.drawable.check_o,
        R.drawable.check_o,
        R.drawable.check_o,
        "한국 교민 수 : 85,125",
        R.drawable.check_o,
        R.drawable.check_o,
        R.drawable.check_o,
        "장기체류자 : 40,699",
        "단기체류자 : 9,101"
    )
    val usaData = arrayListOf<Any>(
        "표면적으로는 기독교 국가. 그러나 법적으로는 기독교 박해 국가로 변질",
        "• 미국은 청교도들이 신앙의 자유를 찾기 위한 모험과 도전으로 세워진 나라이다. \n\n" +
                "• 나라의 시작이 기독교 가치에서 비롯됐기에 세계 어느 나라 중 기독교가 사회 전체에 영향을 미치고 있다. \n\n" +
                "• 미국인의 80%가 본인을 기독교인이라고 지칭하며, 대통령 취임식에서 대통령 당선인은 성경에 손을 올리며 하나님과 국민 앞에 선서를 하기도 한다.\n\n" +
                "• 그러나 현재 미국은 기독교 붕괴의 조짐이 보이고 있다. \n\n" +
                "• 우선 교회에 그 현상이 나타나고 있다. 팬데믹 전인 2019년에도 1년에 4,500개의 교회가 문을 닫았으며, 코로나 이후에는 그 수가 2~3배 증가했다. \n\n" +
                "• 특히 교인의 지속적인 감소로 재정난을 겪어 교회 운영을 포기하는 수백년된 대형교회들도 점차 늘어나는 추세이다. \n\n" +
                "• 이와 함께 미국 목회자들의 신앙관이 흔들리고 있다. \n\n" +
                "• 2022년 2월 애리조나 크리스천대학 문화연구센터(CRC)에서 1,000명의 목회자들을 대상으로 조사한 설문조사 결과에 따르면 성경적 세계관을 갖고 있다고 대답한 목회자는 고작 37% 밖에 되지 않는다.\n\n" +
                "• 더 큰 문제는 ‘차별금지법’이라는 이름으로 복음을 법적으로 탄압하는 법안이 각 주마다 발현되고 있다는 것이다.\n\n" +
                "• 실례로 오리건주에서 레즈비언 손님의 웨딩 케이크 제작 요청을 신앙적 양심에 따라 거부한 가게 주인이 차별금지법 위반으로 고소당했으며, 13만 5천 달러의 벌금형을 선고받은 일이 있다.",
        R.drawable.usa_graph_religion,
        R.drawable.check_o,
        R.drawable.check_o,
        R.drawable.check_o,
        R.drawable.check_x,
        "한국 교민 수 : 2,546,982",
        R.drawable.check_x,
        R.drawable.check_x,
        R.drawable.check_o,
        "장기체류자 : 69,048",
        "단기체류자 : 69,048"
    )

    companion object {
        const val BUNDLE_KEY_COUNTRY_NAME = "BUNDLE_KEY_COUNTRY_NAME"

        fun newInstance(countryName: String): EvanTabFragment {
            val args = Bundle()

            args.putString(BUNDLE_KEY_COUNTRY_NAME, countryName)
            val fragment = EvanTabFragment()
            fragment.arguments = args
            return fragment
            1111111111111111111111111111111111111111111
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
        mBinding = FragmentEvanTabBinding.inflate(layoutInflater)
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

    // TEST_DATA 국가 > 국가상세 > EVAN 탭
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
        binding.subTitle.text = dataList[0].toString() // 전도 서브타이틀
        binding.evanContent.text = dataList[1].toString() // 전도 내용글
        binding.religionGraph.setImageResource(dataList[2].toString().toInt()) // 종교 분포 그래프
        binding.checkIcon11.setImageResource(dataList[3].toString().toInt()) // 예원 파송/협력선교사 
        binding.checkIcon12.setImageResource(dataList[4].toString().toInt()) // 전도협회 파송선교
        binding.checkIcon13.setImageResource(dataList[5].toString().toInt()) // 세계선교대회 참석자
        binding.checkIcon14.setImageResource(dataList[6].toString().toInt()) // RTS
        binding.label15.text = dataList[7].toString() // 한국 교민 수
        binding.checkIcon16.setImageResource(dataList[8].toString().toInt()) // 유니월드 연계사업
        binding.checkIcon17.setImageResource(dataList[9].toString().toInt()) // KOICA 중점 협력국

        binding.checkIcon21.setImageResource(dataList[10].toString().toInt()) // RTS 재학생 여부
        binding.label22.text = dataList[11].toString() // 장기체류자
        binding.label23.text = dataList[12].toString() // 단기체류자 
    }
}