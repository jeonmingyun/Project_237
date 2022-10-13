package com.coram.spy237.ui.country_info.tabs

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.coram.spy237.R
import com.coram.spy237.databinding.FragmentEconomyTabBinding

class EconomyTabFragment : Fragment() {
    // view binding
    private var mBinding: FragmentEconomyTabBinding? = null
    private val binding get() = mBinding!!

    private var bundleCountryName = "마다가스카르"

    /* TEST_DATA 국가 > 국가상세 > ECONOMY 탭
    * {
    *   경재 내용글, GDP, GDP 년도, 1인당 GDP, 1인당 GDP 년도,
    *   GDP 성장률 그래프, 수출 그래프, 수출 내용글, 수입 그래프, 수입 내용글
    * }
    * */
    val madaData = arrayListOf<Any>(
        "• 세계 최빈국 중 하나로, 철도와 도로교통은 매우 열악하여 비포장된 구간이 대다수이다. 미개발 천연자원이 풍부하나, 자본시장의 부재, 취약한 사법시스템, 만연한 정부부패 등 경제개발의 걸림돌이 많다.\n\n" +
                "• GDP의 ¼ 이상이 농업으로 인구의 약 80%가 고용되어 있으며 70% 이상의 토지가 농지로 사용된다. 화전 농업을 주로 하기에 수 세기동안 벌목이 이루어져 산림훼손이 심각하며, 장기적 경제성장을 위한 교육, 의료, 환경 개선이 국가적 과제다.\n\n" +
                "• 또한, 세계 바닐라의 80%를 생산하는 등 한 상품에의 의존도가 높아 외환이 취약하다. 금융부문의 취약성 때문에 인플레이션 통제를 위한 통화정책의 효과성도 제한적이다.",
        "41.82B",
        "2020y",
        "1,500",
        "2020y",
        R.drawable.mada_gdp_grade,
        R.drawable.mada_graph_export,
        "주요 수출품 : 바닐라, 니켈, 골드, 의류, 원석\n" +
                "주요 수출 대상국 : 미국, 프랑스, 아랍에미리트",
        R.drawable.mada_graph_income,
        "주요 수입품 : 정제 석유, 쌀, 자동차, 포장 의약품\n" +
                "주요 수입 대상국 : 중국, 프랑스, 아랍에미리트"
    )
    val ukData = arrayListOf<Any>(
        "• 2017년 기준 우크라이나의 산업 구조는 농업 12.2%, 제조업, 에너지 등이 28.6%, 서비스가 60%를 차지하였다. 농업의 경우 연간 곡물 수출량이 4천만 톤을 상회하여 세계 2위에 이른다.\n\n" +
                "• 우크라이나 하면 빼놓을 수 없는 분야가 항공 산업과 우주 산업인데, 항공기 및 우주 발사체를 자체적으로 설계, 제작할 수 있는 세계 8대 국가 중의 하나로서 항공 산업에 종사하는 기업만 70여개, 전문 인력은 8만 여명에 달한다.",
        "113M",
        "",
        "13",
        "",
        R.drawable.uk_gdp_grade,
        R.drawable.uk_graph_export,
        "주요 수출품 : 옥수수, 해바라기씨유, 철 및 철제품\n" +
                "주요 수출 대상국 : 러시아, 중국, 독일",
        R.drawable.uk_graph_income,
        "주요 수입품 : 정제 석유, 자동차, 포장 의약품\n" +
                "주요 수입 대상국 : 중국, 러시아, 독일"
    )
    val philData = arrayListOf<Any>(
        "• 필리핀의 경제는 인구의 1/10 수준인 약 1,000만명의 필리핀 노동자들이 해외에서 노동을 하고 이들이 다시 필리핀으로 송금해오는 자본을 통하여 내수를 증진시키고 있다.\n\n" +
                "• 또한 민다나오 섬에는 다국적 농업 기업들이 플랜테이션을 건설하였고 필리핀 농업생산량의 90% 이상을 담당하며, 여기서 나온 바나나의 생산률은 세계 3위, 파인애플, 코코넛 생산률은 2위이다. \n\n" +
                "• 여전히 지주와 소작농제가 존재하고 있으며 지주들이 정계에 진출하고 세습시키고 그로 인해 빈부의 격차는 더해지고, 필리핀의 경제 발전을 저해하고 있다.",
        "113M",
        "",
        "13",
        "",
        R.drawable.phil_gdp_grade,
        R.drawable.phil_graph_export,
        "주요 수출품 : 집적회로, 사무기기/부품, 절연배선\n" +
                "주요 수출 대상국 : 중국, 미국, 일본",
        R.drawable.phil_graph_income,
        "주요 수입품 : 집적회로, 정제석유, 자동차\n" +
                "주요 수입 대상국 : 중국, 일본, 한국"
    )
    val usaData = arrayListOf<Any>(
        "• 미국은 세계 최대 규모의 GDP를 자랑한다. \n\n" +
                "• 이는 풍부한 자원과 자유무역, 기술혁신이 뒷받침된 국내 산업 및 해외에서의 활발한 투자활동, 낮은 인플레이션, 견실한 금융시장으로 대표되는 미국 고유의 자본주의 체제에 의한 것이다. \n\n" +
                "• 또한 세계 최대 재화 수입국이며, 수출 규모는 중국에 이어 2위를 차지한다. \n\n" +
                "• 주요 무역 상대국으로는 캐나다, 중국, 멕시코, 일본, 독일이 있다. \n\n" +
                "• 특히 미국은 전 세계 경제 중심지로 통한다. 세계 최고 금융 중심 도시를 조사한 ‘국제금융센터지수(GFCI) 26호’ 보고서에 따르면 미국 뉴욕은 2017년, 2018년, 2019년 3년 간 영국 런던을 누르고 104개 도시 가운데 1위를 차지한바 있다.",
        "19,846B",
        "2020y",
        "60,200",
        "2020y",
        R.drawable.usa_gdp_grade,
        R.drawable.usa_graph_export,
        "주요 수출품 : 정제 석유, 원유, 자동차 및 차량 부품\n" +
                "주요 수출 대상국 : 캐나다, 멕시코, 중국",
        R.drawable.usa_graph_income,
        "주요 수입품 : 자동차, 원유, 컴퓨터, 방송 장비\n" +
                "주요 수입 대상국 : 중국, 멕시코, 캐나다"
    )

    companion object {
        const val BUNDLE_KEY_COUNTRY_NAME = "BUNDLE_KEY_COUNTRY_NAME"

        fun newInstance(countryName: String): EconomyTabFragment {
            val args = Bundle()

            args.putString(BUNDLE_KEY_COUNTRY_NAME, countryName)
            val fragment = EconomyTabFragment()
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
        mBinding = FragmentEconomyTabBinding.inflate(layoutInflater)
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

    // TEST_DATA 국가 > 국가상세 > ECONOMY 탭
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
        binding.economyContent.text = dataList[0].toString() // 경제 내용글
        binding.textView3.text = dataList[1].toString() // GDP
        binding.textView411.text = dataList[2].toString() // GDP 년도
        binding.textView4.text = dataList[3].toString() // 1인당 GDP
        binding.textView41.text = dataList[4].toString() // 1인당 GDP 년도
        binding.gdpGraph.setImageResource(dataList[5].toString().toInt()) // GDP 성장률 그래프
        binding.exportGraph.setImageResource(dataList[6].toString().toInt()) // 수출 그래프
        binding.exportContent.text = dataList[7].toString() // 수출 내용글
        binding.incomeGraph.setImageResource(dataList[8].toString().toInt()) // 수입 그래프
        binding.incomeContent.text = dataList[9].toString() // 수입 내용글
    }
}