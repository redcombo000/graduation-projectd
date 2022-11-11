package com.example.workcare.view.group.info.dialog

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import com.example.workcare.databinding.DialogMessageBinding

class DialogMessage(val onConfirm: () -> Unit) : DialogFragment() {

    private lateinit var mBinding: DialogMessageBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        mBinding = DialogMessageBinding.inflate(inflater, container, false)
        val view = mBinding.root

        init()
        return view
    }

//    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
//        val dialog = super.onCreateDialog(savedInstanceState)
//        dialog.window?.requestFeature(Window.FEATURE_NO_TITLE)
//        return dialog
//    }

//    override fun onStart() {
//        super.onStart()
//
//        dialog?.window?.setLayout(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.WRAP_CONTENT)
//        dialog?.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
//
//    }

//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        setStyle(DialogFragment.STYLE_NO_TITLE, R.style.FullDialog)
//
//
//    }

    private fun init() {

        isCancelable = true

        mBinding.imgClose.setOnClickListener {
            dismiss()
        }

        mBinding.btnConfirm.setOnClickListener {
            onConfirm()
            dismiss()
        }
    }

    override fun dismiss() {
        onConfirm()
        super.dismiss()
    }

}