package com.target.targetcasestudy.ui.payment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.DialogFragment
import com.target.targetcasestudy.R
import com.target.targetcasestudy.data.validateCreditCard
import com.target.targetcasestudy.databinding.DialogPaymentBinding
import com.target.targetcasestudy.utils.autoCleared

/**
 * Dialog that displays a minimal credit card entry form.
 *
 * Your task here is to enable the "submit" button when the credit card number is valid and
 * disable the button when the credit card number is not valid.
 *
 * You do not need to input any of your actual credit card information. See `Validators.kt` for
 * info to help you get fake credit card numbers.
 *
 * You do not need to make any changes to the layout of this screen (though you are welcome to do
 * so if you wish).
 */

class PaymentDialogFragment : DialogFragment() {

    private var binding: DialogPaymentBinding by autoCleared()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DialogPaymentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.cardNumber.addTextChangedListener { cardNumber ->
            if (cardNumber?.length in 13..19) {
                if (validateCreditCard(cardNumber.toString())) {
                    binding.submit.isEnabled = true
                }
            }
        }

        binding.submit.setOnClickListener {
            Toast.makeText(activity, getString(R.string.thank_you), Toast.LENGTH_SHORT).show()
            dismiss()
        }

        binding.cancel.setOnClickListener {
            dismiss()
        }
    }
}