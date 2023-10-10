package com.einvoicing.applicaiton.views.customerupload;

import com.einvoicing.applicaiton.data.Customer;
import com.einvoicing.applicaiton.services.CustomerService;
import com.einvoicing.applicaiton.views.MainLayout;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
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
import com.vaadin.flow.router.BeforeEnterEvent;
import com.vaadin.flow.router.BeforeEnterObserver;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.spring.data.VaadinSpringDataHelpers;
import jakarta.annotation.security.PermitAll;
import java.util.Optional;
import org.springframework.data.domain.PageRequest;
import org.springframework.orm.ObjectOptimisticLockingFailureException;

@PageTitle("Customer Upload")
@Route(value = "customer-upload/:customerID?/:action?(edit)", layout = MainLayout.class)
@PermitAll
public class CustomerUploadView extends Div implements BeforeEnterObserver {

    private final String CUSTOMER_ID = "customerID";
    private final String CUSTOMER_EDIT_ROUTE_TEMPLATE = "customer-upload/%s/edit";

    private final Grid<Customer> grid = new Grid<>(Customer.class, false);

    private TextField source;
    private TextField idType;
    private TextField idNumber;
    private TextField nameEng;
    private TextField nameArb;
    private TextField buildingNumber;
    private TextField unitNumber;
    private TextField additionalNumber;
    private TextField streetEng;
    private TextField streetArb;
    private TextField distrcitEng;
    private TextField districtArb;
    private TextField cityEng;
    private TextField cityArb;
    private TextField country;
    private TextField postalCode;
    private TextField vatNumber;
    private TextField email;
    private TextField phone;

    private final Button cancel = new Button("Cancel");
    private final Button save = new Button("Save");

    private final BeanValidationBinder<Customer> binder;

    private Customer customer;

    private final CustomerService customerService;

    public CustomerUploadView(CustomerService customerService) {
        this.customerService = customerService;
        addClassNames("customer-upload-view");

        // Create UI
        SplitLayout splitLayout = new SplitLayout();

        createGridLayout(splitLayout);
        createEditorLayout(splitLayout);

        add(splitLayout);

        // Configure Grid
        grid.addColumn("source").setAutoWidth(true);
        grid.addColumn("idType").setAutoWidth(true);
        grid.addColumn("idNumber").setAutoWidth(true);
        grid.addColumn("nameEng").setAutoWidth(true);
        grid.addColumn("nameArb").setAutoWidth(true);
        grid.addColumn("buildingNumber").setAutoWidth(true);
        grid.addColumn("unitNumber").setAutoWidth(true);
        grid.addColumn("additionalNumber").setAutoWidth(true);
        grid.addColumn("streetEng").setAutoWidth(true);
        grid.addColumn("streetArb").setAutoWidth(true);
        grid.addColumn("distrcitEng").setAutoWidth(true);
        grid.addColumn("districtArb").setAutoWidth(true);
        grid.addColumn("cityEng").setAutoWidth(true);
        grid.addColumn("cityArb").setAutoWidth(true);
        grid.addColumn("country").setAutoWidth(true);
        grid.addColumn("postalCode").setAutoWidth(true);
        grid.addColumn("vatNumber").setAutoWidth(true);
        grid.addColumn("email").setAutoWidth(true);
        grid.addColumn("phone").setAutoWidth(true);
        grid.setItems(query -> customerService.list(
                PageRequest.of(query.getPage(), query.getPageSize(), VaadinSpringDataHelpers.toSpringDataSort(query)))
                .stream());
        grid.addThemeVariants(GridVariant.LUMO_NO_BORDER);

        // when a row is selected or deselected, populate form
        grid.asSingleSelect().addValueChangeListener(event -> {
            if (event.getValue() != null) {
                UI.getCurrent().navigate(String.format(CUSTOMER_EDIT_ROUTE_TEMPLATE, event.getValue().getId()));
            } else {
                clearForm();
                UI.getCurrent().navigate(CustomerUploadView.class);
            }
        });

        // Configure Form
        binder = new BeanValidationBinder<>(Customer.class);

        // Bind fields. This is where you'd define e.g. validation rules

        binder.bindInstanceFields(this);

        cancel.addClickListener(e -> {
            clearForm();
            refreshGrid();
        });

        save.addClickListener(e -> {
            try {
                if (this.customer == null) {
                    this.customer = new Customer();
                }
                binder.writeBean(this.customer);
                customerService.update(this.customer);
                clearForm();
                refreshGrid();
                Notification.show("Data updated");
                UI.getCurrent().navigate(CustomerUploadView.class);
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
        Optional<Long> customerId = event.getRouteParameters().get(CUSTOMER_ID).map(Long::parseLong);
        if (customerId.isPresent()) {
            Optional<Customer> customerFromBackend = customerService.get(customerId.get());
            if (customerFromBackend.isPresent()) {
                populateForm(customerFromBackend.get());
            } else {
                Notification.show(String.format("The requested customer was not found, ID = %s", customerId.get()),
                        3000, Notification.Position.BOTTOM_START);
                // when a row is selected but the data is no longer available,
                // refresh grid
                refreshGrid();
                event.forwardTo(CustomerUploadView.class);
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
        source = new TextField("Source");
        idType = new TextField("Id Type");
        idNumber = new TextField("Id Number");
        nameEng = new TextField("Name Eng");
        nameArb = new TextField("Name Arb");
        buildingNumber = new TextField("Building Number");
        unitNumber = new TextField("Unit Number");
        additionalNumber = new TextField("Additional Number");
        streetEng = new TextField("Street Eng");
        streetArb = new TextField("Street Arb");
        distrcitEng = new TextField("Distrcit Eng");
        districtArb = new TextField("District Arb");
        cityEng = new TextField("City Eng");
        cityArb = new TextField("City Arb");
        country = new TextField("Country");
        postalCode = new TextField("Postal Code");
        vatNumber = new TextField("Vat Number");
        email = new TextField("Email");
        phone = new TextField("Phone");
        formLayout.add(source, idType, idNumber, nameEng, nameArb, buildingNumber, unitNumber, additionalNumber,
                streetEng, streetArb, distrcitEng, districtArb, cityEng, cityArb, country, postalCode, vatNumber, email,
                phone);

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

    private void populateForm(Customer value) {
        this.customer = value;
        binder.readBean(this.customer);

    }
}
