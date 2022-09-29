package com.coram.spy237.ui.pray_note

import android.app.Activity
import android.content.Intent
import android.database.Cursor
import android.os.Bundle
import android.text.Spannable
import android.text.style.BackgroundColorSpan
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.coram.spy237.R
import com.coram.spy237.databinding.FragmentPrayNoteBinding
import com.coram.spy237.db.DbOpenHelper
import com.coram.spy237.db.DbTable
import com.coram.spy237.model.db.HighlightModel
import com.coram.spy237.ui.pray_note.callback.MyCallback
import com.coram.spy237.util.DateFormatUtil
import com.coram.spy237.util.PrefManager
import com.coram.spy237.util.Utils
import java.util.*


class PrayNoteFragment : Fragment(), View.OnClickListener {
    // view binding
    private var mBinding: FragmentPrayNoteBinding? = null
    private val binding get() = mBinding!!

    // DB
    private lateinit var dbHelper: DbOpenHelper

    // sample
    private val prayNoteList = arrayListOf<String>(
        "2022-9-29",
        "2022-9-30",
        "2022-10-1",
    )
    private val prayNoteMap = hashMapOf<String, Int>(
        prayNoteList[0] to R.drawable.pray_note_1,
        prayNoteList[1] to R.drawable.pray_note_2,
        prayNoteList[2] to R.drawable.pray_note_3
    )
    private val prayDateList = arrayListOf(
        DateFormatUtil.formatChangeDateString(prayNoteList[0], "yyyy-M-dd", "yyyy년 M월 d일"),
        DateFormatUtil.formatChangeDateString(prayNoteList[1], "yyyy-M-dd", "yyyy년 M월 d일"),
        DateFormatUtil.formatChangeDateString(prayNoteList[2], "yyyy-M-dd", "yyyy년 M월 d일")
    )
    private val prayTitleList = arrayListOf(
        "최고의 상급",
        "골로새서",
        "블레셋 문화를 이긴 성전"
    )
    private val prayBibleList = arrayListOf(
        "마태복음 10:40~42 | 너희를 영접하는 자는 나를 영접하는 것이요 나를 영접하는 자는 나를 보내신 이를 영접하는 것이니라 선지자의 이름으로 선지자를 영접하는 자는 선지자의 상을 받을 것이요 의인의 이름으로 의인을 영접하는 자는 의인의 상을 받을 것이요 또 누구든지 제자의 이름으로 이 작은 자 중 하나에게 냉수 한 그릇이라도 주는 자는 내가 진실로 너희에게 이르노니 그 사람이 결단코 상을 잃지 아니하리라 하시니라",
        "골로새서 1:3~4 | 우리가 너희를 위하여 기도할 때마다 하나님 곧 우리 주 예수 그리스도의 아버지께 감사하노라 이는 그리스도 예수 안에 너희의 믿음과 모든 성도에 대한 사랑을 들었음이요",
        "역대상 29:10~17 | 나와 내 백성이 무엇이기에 이처럼 즐거운 마음으로 드릴 힘이 있었나이까 모든 것이 주께로 말미암았사오니 우리가 주의 손에서 받은 것으로 주께 드렸을 뿐이니이다"
    )
    private val prayTitle01List = arrayListOf(
        "1. 역사적 이유 - 복음이 증거 될 때 해방되는 역사",
        "1. 예배와 복음의 말씀",
        "1. 다윗에게 미리 응답하신 하나님"
    )
    private val prayTitle02List = arrayListOf(
        "2. 예수님이 말씀하신 이유",
        "2. 골로새서",
        "2. 위기 때 응답하신 하나님"
    )
    private val prayTitle03List = arrayListOf(
        "3. 마지막 시대에 대한 이유",
        "3. 흐름",
        "3. 아무것도 없는 상태에서 응답하신 하나님"
    )
    private val prayComment01List = arrayListOf(
        "모세를 통해 애굽에 피 제사의 언약이 전달되자 출애굽의 기적이 일어났습니다. 바울을 통해 로마에 복음이 전달되자 250년 만에 로마가 복음 앞에 무릎을 꿇었습니다. 오늘 이 복음이 우리 현장에 증거될 때 현장의 흑암이 무너지고 성경에 나타난 출애굽과 출바벨론, 출로마의 역사가 시작될 것입니다.",
        "사탄은 예배와 말씀을 빼앗아 가려고 합니다. 예배할 때 5력 영력, 지력, 체력, 경제력, 인력이 나타나고 제자가 세워지며 치유가 일어나기 때문입니다. 이를 아는 우리는 교회와 현장, 전체 메시지 흐름 속에 있어야 합니다. 그러면 문제가 왔을 때 하나님의 말씀에 담긴 능력이 답으로 다가옵니다. 이 답을 가진 성경의 Remnant 7명 요셉, 모세, 사무엘, 다윗, 엘리사, 이사야, 바울은 문제 앞에 흔들리지 않았습니다. 또, 처음 하는 일은 익숙한 것처럼, 익숙한 일은 처음 하는 것처럼 하면 됩니다.\"",
        "하나님은 다윗이 성전을 위한 영적 싸움을 할 것을 아시고 미리 응답하셨습니다. 성전의 언약을 잡은 다윗이 목동으로 있을 때부터 축복하신 것입니다. 하나님은 다윗에게 영적 축복뿐만 아니라 그에게 필요한 기능의 축복까지 허락하셨습니다. 이처럼 하나님은 세계를 살리는 성전운동을 하기 이전에 우리를 미리 축복하시고 우리에게 응답하기 원하십니다. 우리는 하나님이 주신 축복과 응답을 가지고 세계 우상과 영적 전쟁을 하면 됩니다."
    )
    private val prayComment02List = arrayListOf(
        "복음은 작은 씨앗 같지만, 현장에서 큰 나무가 되는 결실을 맺습니다. 복음은 보이지 않던 누룩이 부풀어 오르듯이 퍼져서 현장을 살립니다. 현장의 모든 흑암 세력도 무너뜨립니다. 예수님은 모든 저주를 해결할 십자가의 비밀도 말씀하셨습니다. 그리고 부활하셔서 40일 동안 그리스도, 하나님 나라, 성령 충만의 비밀을 전달하셨습니다. 이 복음의 비밀을 가진 사람이 바로 전도자입니다. 예수님은 전도자에게 냉수 한 그릇이라도 대접하는 사람, 즉 전도자의 대열에 선 사람을 돕고 복음운동에 헌신하는 사람은 상을 잃지 않는다고 말씀하셨습니다.",
        "하나님은 우리를 한 시대를 소통하는 두기고로 부르셨습니다. 하나님의 말씀은 포럼할 때 깊이 각인됩니다. 바울은 골로새 성도들과 함께 기도하며 감사와 사랑을 포럼했습니다.1:3~4 바울은 감춰진 것이 인생의 모든 것을 좌우하는 것을 알았습니다. 그래서 그리스도 안에 감추어져 있는 복음의 지식과 지혜에 대해 포럼했습니다.2:2~3 역사 속의 영웅들과 성공자들은 고통을 당하다가 세상을 떠났지만, 바울은 위의 것과 땅의 것을 포럼했습니다.3:2 두기고는 이런 바울의 사정을 전하는 자, 사랑 받는 형제, 신실한 일꾼, 종된 자, 특별하게 쓰는 자, 전체의 사정을 전하는 자, 위로를 위해 보내는 자였습니다.",
        "하나님은 메이슨 전쟁을 해야 할 다윗이 위기에 처했을 때에도 응답하셨습니다. 골리앗이 공격했을 때 하나님은 언약을 붙잡은 다윗을 통해 이스라엘의 위기를 해결하셨습니다. 악령에 사로잡힌 사울 왕이 다윗을 죽이려고 할 때에도 하나님은 다윗에게 중요한 응답들을 예비하셨습니다. 다윗은 언제든지 위기 앞에서 절망하고 넘어지지 않고 하나님의 언약을 확인하는 기회로 삼았습니다."
    )
    private val prayComment03List = arrayListOf(
        "예수님은 말세에 고통의 때가 오고 전쟁, 기근, 지진 등이 일어날 것이라고 말씀하셨습니다. 그러나 분명한 것은 복음이 증언된 이후에 끝이 온다는 것입니다. 하나님은 우리를 현장과 시대에 복음을 전달할 전도자로 부르셨습니다. 이 사실을 기억하고 오늘 복음, 전도, 언약의 대열에 선다면 하나님은 우리를 통해 237개 나라를 살리실 것입니다.",
        "말씀, 기도, 전도 속에 전부 담겨 있습니다. 우리는 이 사실을 기억하며 가장 먼저 복음의 흐름 속에서 말씀을 보아야 합니다. 이때 하나님이 응답하시는 기도제목을 발견할 수 있습니다. 이 흐름을 따라 가야 합니다. 그리고 하나님이 예비하신 전도의 흐름을 따라 가야 합니다. 이때 Remnant는 미래를 보게 되고, 중직자의 현장에는 하나님의 역사가 일어나며, 목회자의 강단은 살아납니다.",
        "하나님은 메이슨 전쟁을 하는 사람에게 아무것도 없어도 승리하게 하십니다. 즉, 하나님은 메이슨 전쟁의 언약을 붙잡은 사람을 통해 모든 일을 진행해 나가십니다. 이 언약을 가슴에 품었던 다윗 한 사람 때문에 블레셋을 꺾으셨고, 후대를 위해 성전을 짓도록 최고의 지혜와 지식을 허락하셨습니다. 오늘 우리가 이 언약을 가슴에 품고 기도한다면 하나님은 동일하게 역사 하실 것입니다."
    )

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // view binding
        mBinding = FragmentPrayNoteBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //db
        dbHelper = DbOpenHelper(context)

        setOnPagingColor(0)
        setOnHighlight()

        binding.toolbarMenuBtn.setOnClickListener(this)
        binding.prevBtn.setOnClickListener(this)
        binding.nextBtn.setOnClickListener(this)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        // view binding
        mBinding = null
    }

    override fun onClick(v: View?) {
        var currentPage = getCurrentPage()
        when (v) {
            binding.toolbarMenuBtn -> {
                startActivityForResult(Intent(context, PraySetActivity::class.java), 100)
            }
            binding.prevBtn -> {
                if (currentPage == 0) return
                currentPage -= 1
            }
            binding.nextBtn -> {
                if (currentPage == 2) return
                currentPage += 1
            }
        }
        setOnPagingColor(currentPage)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == 100) {
            if (resultCode == Activity.RESULT_OK) {
                val lang = data!!.extras!!.getString("lang").toString()
                val date = data!!.extras!!.getString("date").toString()

                if (prayNoteMap[date] != null) {
                    setOnPagingColor(prayNoteList.indexOf(date))
                } else {
                    Utils.onToast(context, "해당 날짜 기도수첩이 없습니다.")
                }
            }
        }
    }

    private fun getCurrentPage(): Int {
        val position = PrefManager.getInt(requireContext(), PrefManager.PREF_PRAY_NOTE_CURRENT_PAGE)

        return if (position == -1) 0 else position
    }

    private fun setCurrentPage(currentPage: Int) {
        PrefManager.setInt(requireContext(), PrefManager.PREF_PRAY_NOTE_CURRENT_PAGE, currentPage)
    }

    private fun initHighlight(modelList: ArrayList<HighlightModel>) {
        val list = modelList.filter {
            it.position == getCurrentPage()
        }
        for (model in list) {
            val mTextView = view?.findViewById<TextView>(model.textId)
            val wordToSpan: Spannable = mTextView!!.text as Spannable
            wordToSpan.setSpan(
                BackgroundColorSpan(model.color),
                model.start,
                model.end,
                Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
            )
            mTextView.text = wordToSpan
        }
    }

    private fun getAllHighlight(): ArrayList<HighlightModel> {
        val cursor: Cursor = dbHelper.selectAllHighlight()
        val modelList = arrayListOf<HighlightModel>()

        while (cursor.moveToNext()) {
            modelList += HighlightModel(
                cursor.getInt(cursor.getColumnIndexOrThrow(DbTable.Highlight.COLUMN_ID)),
                cursor.getInt(cursor.getColumnIndexOrThrow(DbTable.Highlight.COLUMN_POSITION)),
                cursor.getInt(cursor.getColumnIndexOrThrow(DbTable.Highlight.COLUMN_TEXT_ID)),
                cursor.getInt(cursor.getColumnIndexOrThrow(DbTable.Highlight.COLUMN_START)),
                cursor.getInt(cursor.getColumnIndexOrThrow(DbTable.Highlight.COLUMN_END)),
                cursor.getInt(cursor.getColumnIndexOrThrow(DbTable.Highlight.COLUMN_COLOR)),
            )
        }
        cursor.close()
        return modelList
    }

    private fun setOnHighlight() {
        binding.prayNoteDate.customSelectionActionModeCallback =
            MyCallback(requireContext(), binding.prayNoteDate)
        binding.prayNoteTitle.customSelectionActionModeCallback =
            MyCallback(requireContext(), binding.prayNoteTitle)
        binding.bible.customSelectionActionModeCallback =
            MyCallback(requireContext(), binding.bible)
        binding.prayNoteTitle01.customSelectionActionModeCallback =
            MyCallback(requireContext(), binding.prayNoteTitle01)
        binding.prayNoteTitle02.customSelectionActionModeCallback =
            MyCallback(requireContext(), binding.prayNoteTitle02)
        binding.prayNoteTitle03.customSelectionActionModeCallback =
            MyCallback(requireContext(), binding.prayNoteTitle03)
        binding.prayNoteComment01.customSelectionActionModeCallback =
            MyCallback(requireContext(), binding.prayNoteComment01)
        binding.prayNoteComment02.customSelectionActionModeCallback =
            MyCallback(requireContext(), binding.prayNoteComment02)
        binding.prayNoteComment03.customSelectionActionModeCallback =
            MyCallback(requireContext(), binding.prayNoteComment03)
    }

    private fun setOnPagingColor(position: Int) {
        setCurrentPage(position)
        when (position) {
            0 -> {
                binding.prayNoteDate.text = prayDateList[0]
                binding.prayNoteTitle.text = prayTitleList[0]
                binding.bible.text = prayBibleList[0]
                binding.prayNoteTitle01.text = prayTitle01List[0]
                binding.prayNoteTitle02.text = prayTitle02List[0]
                binding.prayNoteTitle03.text = prayTitle03List[0]
                binding.prayNoteComment01.text = prayComment01List[0]
                binding.prayNoteComment02.text = prayComment02List[0]
                binding.prayNoteComment03.text = prayComment03List[0]
                binding.pagingIndicator1.setBackgroundDrawable(resources.getDrawable(R.drawable.btn_page_on))
                binding.pagingIndicator2.setBackgroundDrawable(resources.getDrawable(R.drawable.btn_page_off))
                binding.pagingIndicator3.setBackgroundDrawable(resources.getDrawable(R.drawable.btn_page_off))
            }
            1 -> {
                binding.prayNoteDate.text = prayDateList[1]
                binding.prayNoteTitle.text = prayTitleList[1]
                binding.bible.text = prayBibleList[1]
                binding.prayNoteTitle01.text = prayTitle01List[1]
                binding.prayNoteTitle02.text = prayTitle02List[1]
                binding.prayNoteTitle03.text = prayTitle03List[1]
                binding.prayNoteComment01.text = prayComment01List[1]
                binding.prayNoteComment02.text = prayComment02List[1]
                binding.prayNoteComment03.text = prayComment03List[1]
                binding.pagingIndicator1.setBackgroundDrawable(resources.getDrawable(R.drawable.btn_page_off))
                binding.pagingIndicator2.setBackgroundDrawable(resources.getDrawable(R.drawable.btn_page_on))
                binding.pagingIndicator3.setBackgroundDrawable(resources.getDrawable(R.drawable.btn_page_off))

            }
            2 -> {
                binding.prayNoteDate.text = prayDateList[2]
                binding.prayNoteTitle.text = prayTitleList[2]
                binding.bible.text = prayBibleList[2]
                binding.prayNoteTitle01.text = prayTitle01List[2]
                binding.prayNoteTitle02.text = prayTitle02List[2]
                binding.prayNoteTitle03.text = prayTitle03List[2]
                binding.prayNoteComment01.text = prayComment01List[2]
                binding.prayNoteComment02.text = prayComment02List[2]
                binding.prayNoteComment03.text = prayComment03List[2]
                binding.pagingIndicator1.setBackgroundDrawable(resources.getDrawable(R.drawable.btn_page_off))
                binding.pagingIndicator2.setBackgroundDrawable(resources.getDrawable(R.drawable.btn_page_off))
                binding.pagingIndicator3.setBackgroundDrawable(resources.getDrawable(R.drawable.btn_page_on))
            }
        }
        initHighlight(getAllHighlight())
    }
}