package com.axelor.apps.account.service.move;

import com.axelor.apps.account.db.MoveLine;
import com.axelor.apps.account.service.invoice.InvoiceTermService;
import com.google.inject.Inject;
import java.math.BigDecimal;

public class MoveLineInvoiceTermServiceImpl implements MoveLineInvoiceTermService {
  protected InvoiceTermService invoiceTermService;

  @Inject
  public MoveLineInvoiceTermServiceImpl(InvoiceTermService invoiceTermService) {
    this.invoiceTermService = invoiceTermService;
  }

  @Override
  public void generateDefaultInvoiceTerm(MoveLine moveLine) {
    BigDecimal amount =
        moveLine.getCredit().signum() == 0 ? moveLine.getDebit() : moveLine.getCredit();

    invoiceTermService.createInvoiceTerm(
        null,
        moveLine,
        null,
        null,
        moveLine.getMove().getPaymentMode(),
        moveLine.getDate(),
        null,
        amount,
        BigDecimal.valueOf(100),
        false);
  }
}