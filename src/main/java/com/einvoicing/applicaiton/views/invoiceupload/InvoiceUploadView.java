package com.einvoicing.applicaiton.views.invoiceupload;

import com.einvoicing.applicaiton.data.InvoiceLine;
import com.einvoicing.applicaiton.services.InvoiceLineService;
import com.einvoicing.applicaiton.views.MainLayout;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.datepicker.DatePicker;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.grid.GridVariant;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.notification.Notification.Position;
import com.vaadin.flow.component.notification.NotificationVariant;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.splitlayout.SplitLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.BeanValidationBinder;
import com.vaadin.flow.data.binder.ValidationException;
import com.vaadin.flow.data.converter.StringToIntegerConverter;
import com.vaadin.flow.router.BeforeEnterEvent;
import com.vaadin.flow.router.BeforeEnterObserver;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.spring.data.VaadinSpringDataHelpers;
import jakarta.annotation.security.PermitAll;
import java.util.Optional;
import org.springframework.data.domain.PageRequest;
import org.springframework.orm.ObjectOptimisticLockingFailureException;

@PageTitle("Invoice Upload")
@Route(value = "invoice-upload/:invoiceLineID?/:action?(edit)", layout = MainLayout.class)
@PermitAll
public class InvoiceUploadView extends Div implements BeforeEnterObserver {

    private final String INVOICELINE_ID = "invoiceLineID";
    private final String INVOICELINE_EDIT_ROUTE_TEMPLATE = "invoice-upload/%s/edit";

    private final Grid<InvoiceLine> grid = new Grid<>(InvoiceLine.class, false);

    private TextField runNumber;
    private TextField idType;
    private TextField idNumber;
    private TextField customerName;
    private TextField billNumber;
    private TextField lineNo;
    private DatePicker supplyDate;
    private DatePicker supplyEndDate;
    private TextField itemName;
    private TextField quantity;
    private TextField unitPrice;
    private TextField totalPrice;
    private TextField discount;
    private TextField taxableAmount;
    private TextField taxExemptionReason;
    private TextField taxCategory;
    private TextField taxRate;
    private TextField taxAmount;
    private TextField taxInclusiveAmount;
    private TextField invoiceNote;
    private TextField invoiceType;
    private TextField currency;
    private TextField fxRate;
    private TextField invoiceNoteReason;
    private TextField seller;
    private TextField paymentDetails;
    private TextField customerAccount;
    private TextField previousInvoice;
    private TextField previousIssueDate;

    private final Button cancel = new Button("Cancel");
    private final Button save = new Button("Save");

    private final BeanValidationBinder<InvoiceLine> binder;

    private InvoiceLine invoiceLine;

    private final InvoiceLineService invoiceLineService;

    public InvoiceUploadView(InvoiceLineService invoiceLineService) {
        this.invoiceLineService = invoiceLineService;
        addClassNames("invoice-upload-view");

        // Create UI
        SplitLayout splitLayout = new SplitLayout();

        createGridLayout(splitLayout);
        createEditorLayout(splitLayout);

        add(splitLayout);

        // Configure Grid
        grid.addColumn("runNumber").setAutoWidth(true);
        grid.addColumn("idType").setAutoWidth(true);
        grid.addColumn("idNumber").setAutoWidth(true);
        grid.addColumn("customerName").setAutoWidth(true);
        grid.addColumn("billNumber").setAutoWidth(true);
        grid.addColumn("lineNo").setAutoWidth(true);
        grid.addColumn("supplyDate").setAutoWidth(true);
        grid.addColumn("supplyEndDate").setAutoWidth(true);
        grid.addColumn("itemName").setAutoWidth(true);
        grid.addColumn("quantity").setAutoWidth(true);
        grid.addColumn("unitPrice").setAutoWidth(true);
        grid.addColumn("totalPrice").setAutoWidth(true);
        grid.addColumn("discount").setAutoWidth(true);
        grid.addColumn("taxableAmount").setAutoWidth(true);
        grid.addColumn("taxExemptionReason").setAutoWidth(true);
        grid.addColumn("taxCategory").setAutoWidth(true);
        grid.addColumn("taxRate").setAutoWidth(true);
        grid.addColumn("taxAmount").setAutoWidth(true);
        grid.addColumn("taxInclusiveAmount").setAutoWidth(true);
        grid.addColumn("invoiceNote").setAutoWidth(true);
        grid.addColumn("invoiceType").setAutoWidth(true);
        grid.addColumn("currency").setAutoWidth(true);
        grid.addColumn("fxRate").setAutoWidth(true);
        grid.addColumn("invoiceNoteReason").setAutoWidth(true);
        grid.addColumn("seller").setAutoWidth(true);
        grid.addColumn("paymentDetails").setAutoWidth(true);
        grid.addColumn("customerAccount").setAutoWidth(true);
        grid.addColumn("previousInvoice").setAutoWidth(true);
        grid.addColumn("previousIssueDate").setAutoWidth(true);
        grid.setItems(query -> invoiceLineService.list(
                PageRequest.of(query.getPage(), query.getPageSize(), VaadinSpringDataHelpers.toSpringDataSort(query)))
                .stream());
        grid.addThemeVariants(GridVariant.LUMO_NO_BORDER);

        // when a row is selected or deselected, populate form
        grid.asSingleSelect().addValueChangeListener(event -> {
            if (event.getValue() != null) {
                UI.getCurrent().navigate(String.format(INVOICELINE_EDIT_ROUTE_TEMPLATE, event.getValue().getId()));
            } else {
                clearForm();
                UI.getCurrent().navigate(InvoiceUploadView.class);
            }
        });

        // Configure Form
        binder = new BeanValidationBinder<>(InvoiceLine.class);

        // Bind fields. This is where you'd define e.g. validation rules
        binder.forField(lineNo).withConverter(new StringToIntegerConverter("Only numbers are allowed")).bind("lineNo");
        binder.forField(taxRate).withConverter(new StringToIntegerConverter("Only numbers are allowed"))
                .bind("taxRate");

        binder.bindInstanceFields(this);

        cancel.addClickListener(e -> {
            clearForm();
            refreshGrid();
        });

        save.addClickListener(e -> {
            try {
                if (this.invoiceLine == null) {
                    this.invoiceLine = new InvoiceLine();
                }
                binder.writeBean(this.invoiceLine);
                invoiceLineService.update(this.invoiceLine);
                clearForm();
                refreshGrid();
                Notification.show("Data updated");
                UI.getCurrent().navigate(InvoiceUploadView.class);
            } catch (ObjectOptimisticLockingFailureException exception) {
                Notification n = Notification.show(
                        "Error updating the data. Somebody else has updated the record while you were making changes.");
                n.setPosition(Position.MIDDLE);
                n.addThemeVariants(NotificationVariant.LUMO_ERROR);
            } catch (ValidationException validationException) {
                Notification.show("Failed to update the data. Check again that all values are valid");
            }
        });
    }

    @Override
    public void beforeEnter(BeforeEnterEvent event) {
        Optional<Long> invoiceLineId = event.getRouteParameters().get(INVOICELINE_ID).map(Long::parseLong);
        if (invoiceLineId.isPresent()) {
            Optional<InvoiceLine> invoiceLineFromBackend = invoiceLineService.get(invoiceLineId.get());
            if (invoiceLineFromBackend.isPresent()) {
                populateForm(invoiceLineFromBackend.get());
            } else {
                Notification.show(
                        String.format("The requested invoiceLine was not found, ID = %s", invoiceLineId.get()), 3000,
                        Notification.Position.BOTTOM_START);
                // when a row is selected but the data is no longer available,
                // refresh grid
                refreshGrid();
                event.forwardTo(InvoiceUploadView.class);
            }
        }
    }

    private void createEditorLayout(SplitLayout splitLayout) {
        Div editorLayoutDiv = new Div();
        editorLayoutDiv.setClassName("editor-layout");

        Div editorDiv = new Div();
        editorDiv.setClassName("editor");
        editorLayoutDiv.add(editorDiv);

        FormLayout formLayout = new FormLayout();
        runNumber = new TextField("Run Number");
        idType = new TextField("Id Type");
        idNumber = new TextField("Id Number");
        customerName = new TextField("Customer Name");
        billNumber = new TextField("Bill Number");
        lineNo = new TextField("Line No");
        supplyDate = new DatePicker("Supply Date");
        supplyEndDate = new DatePicker("Supply End Date");
        itemName = new TextField("Item Name");
        quantity = new TextField("Quantity");
        unitPrice = new TextField("Unit Price");
        totalPrice = new TextField("Total Price");
        discount = new TextField("Discount");
        taxableAmount = new TextField("Taxable Amount");
        taxExemptionReason = new TextField("Tax Exemption Reason");
        taxCategory = new TextField("Tax Category");
        taxRate = new TextField("Tax Rate");
        taxAmount = new TextField("Tax Amount");
        taxInclusiveAmount = new TextField("Tax Inclusive Amount");
        invoiceNote = new TextField("Invoice Note");
        invoiceType = new TextField("Invoice Type");
        currency = new TextField("Currency");
        fxRate = new TextField("Fx Rate");
        invoiceNoteReason = new TextField("Invoice Note Reason");
        seller = new TextField("Seller");
        paymentDetails = new TextField("Payment Details");
        customerAccount = new TextField("Customer Account");
        previousInvoice = new TextField("Previous Invoice");
        previousIssueDate = new TextField("Previous Issue Date");
        formLayout.add(runNumber, idType, idNumber, customerName, billNumber, lineNo, supplyDate, supplyEndDate,
                itemName, quantity, unitPrice, totalPrice, discount, taxableAmount, taxExemptionReason, taxCategory,
                taxRate, taxAmount, taxInclusiveAmount, invoiceNote, invoiceType, currency, fxRate, invoiceNoteReason,
                seller, paymentDetails, customerAccount, previousInvoice, previousIssueDate);

        editorDiv.add(formLayout);
        createButtonLayout(editorLayoutDiv);

        splitLayout.addToSecondary(editorLayoutDiv);
    }

    private void createButtonLayout(Div editorLayoutDiv) {
        HorizontalLayout buttonLayout = new HorizontalLayout();
        buttonLayout.setClassName("button-layout");
        cancel.addThemeVariants(ButtonVariant.LUMO_TERTIARY);
        save.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        buttonLayout.add(save, cancel);
        editorLayoutDiv.add(buttonLayout);
    }

    private void createGridLayout(SplitLayout splitLayout) {
        Div wrapper = new Div();
        wrapper.setClassName("grid-wrapper");
        splitLayout.addToPrimary(wrapper);
        wrapper.add(grid);
    }

    private void refreshGrid() {
        grid.select(null);
        grid.getDataProvider().refreshAll();
    }

    private void clearForm() {
        populateForm(null);
    }

    private void populateForm(InvoiceLine value) {
        this.invoiceLine = value;
        binder.readBean(this.invoiceLine);

    }
}
