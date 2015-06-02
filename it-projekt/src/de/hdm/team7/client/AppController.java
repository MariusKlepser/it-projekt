package de.hdm.team7.client;

import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.user.client.ui.CellPanel;
import com.google.gwt.user.client.ui.DockPanel;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HasWidgets;
import com.google.gwt.user.client.ui.RootPanel;

import de.hdm.team7.client.event.billOfMaterial.AddBillOfMaterialEvent;
import de.hdm.team7.client.event.billOfMaterial.AddBillOfMaterialEventHandler;
import de.hdm.team7.client.event.billOfMaterial.BillOfMaterialDeletedEvent;
import de.hdm.team7.client.event.billOfMaterial.BillOfMaterialDeletedEventHandler;
import de.hdm.team7.client.event.billOfMaterial.BillOfMaterialUpdatedEvent;
import de.hdm.team7.client.event.billOfMaterial.BillOfMaterialUpdatedEventHandler;
import de.hdm.team7.client.event.billOfMaterial.CreateBillOfMaterialCancelledEvent;
import de.hdm.team7.client.event.billOfMaterial.CreateBillOfMaterialCancelledEventHandler;
import de.hdm.team7.client.event.billOfMaterial.EditBillOfMaterialCancelledEvent;
import de.hdm.team7.client.event.billOfMaterial.EditBillOfMaterialCancelledEventHandler;
import de.hdm.team7.client.event.billOfMaterial.EditBillOfMaterialEvent;
import de.hdm.team7.client.event.billOfMaterial.EditBillOfMaterialEventHandler;
import de.hdm.team7.client.event.component.AddComponentEvent;
import de.hdm.team7.client.event.component.AddComponentEventHandler;
import de.hdm.team7.client.event.component.ComponentDeletedEvent;
import de.hdm.team7.client.event.component.ComponentDeletedEventHandler;
import de.hdm.team7.client.event.component.ComponentUpdatedEvent;
import de.hdm.team7.client.event.component.ComponentUpdatedEventHandler;
import de.hdm.team7.client.event.component.CreateComponentCancelledEvent;
import de.hdm.team7.client.event.component.CreateComponentCancelledEventHandler;
import de.hdm.team7.client.event.component.EditComponentCancelledEvent;
import de.hdm.team7.client.event.component.EditComponentCancelledEventHandler;
import de.hdm.team7.client.event.component.EditComponentEvent;
import de.hdm.team7.client.event.component.EditComponentEventHandler;
import de.hdm.team7.client.event.componentAssembly.AddComponentAssemblyEvent;
import de.hdm.team7.client.event.componentAssembly.AddComponentAssemblyEventHandler;
import de.hdm.team7.client.event.componentAssembly.ComponentAssemblyDeletedEvent;
import de.hdm.team7.client.event.componentAssembly.ComponentAssemblyDeletedEventHandler;
import de.hdm.team7.client.event.componentAssembly.ComponentAssemblyUpdatedEvent;
import de.hdm.team7.client.event.componentAssembly.ComponentAssemblyUpdatedEventHandler;
import de.hdm.team7.client.event.componentAssembly.CreateComponentAssemblyCancelledEvent;
import de.hdm.team7.client.event.componentAssembly.CreateComponentAssemblyCancelledEventHandler;
import de.hdm.team7.client.event.componentAssembly.EditComponentAssemblyCancelledEvent;
import de.hdm.team7.client.event.componentAssembly.EditComponentAssemblyCancelledEventHandler;
import de.hdm.team7.client.event.componentAssembly.EditComponentAssemblyEvent;
import de.hdm.team7.client.event.componentAssembly.EditComponentAssemblyEventHandler;
import de.hdm.team7.client.event.endProduct.AddEndProductEvent;
import de.hdm.team7.client.event.endProduct.AddEndProductEventHandler;
import de.hdm.team7.client.event.endProduct.CreateEndProductCancelledEvent;
import de.hdm.team7.client.event.endProduct.CreateEndProductCancelledEventHandler;
import de.hdm.team7.client.event.endProduct.EditEndProductCancelledEvent;
import de.hdm.team7.client.event.endProduct.EditEndProductCancelledEventHandler;
import de.hdm.team7.client.event.endProduct.EditEndProductEvent;
import de.hdm.team7.client.event.endProduct.EditEndProductEventHandler;
import de.hdm.team7.client.event.endProduct.EndProductDeletedEvent;
import de.hdm.team7.client.event.endProduct.EndProductDeletedEventHandler;
import de.hdm.team7.client.event.endProduct.EndProductUpdatedEvent;
import de.hdm.team7.client.event.endProduct.EndProductUpdatedEventHandler;
import de.hdm.team7.client.presenter.BusinessObjectTreePresenter;
import de.hdm.team7.client.presenter.CreateBillOfMaterialPresenter;
import de.hdm.team7.client.presenter.CreateComponentAssemblyPresenter;
import de.hdm.team7.client.presenter.CreateComponentPresenter;
import de.hdm.team7.client.presenter.CreateEndProductPresenter;
import de.hdm.team7.client.presenter.EditBillOfMaterialPresenter;
import de.hdm.team7.client.presenter.EditComponentAssemblyPresenter;
import de.hdm.team7.client.presenter.EditComponentPresenter;
import de.hdm.team7.client.presenter.EditEndProductPresenter;
import de.hdm.team7.client.presenter.Presenter;
import de.hdm.team7.client.ui.views.BusinessObjectTreeView;
import de.hdm.team7.client.ui.views.CreateBillOfMaterialView;
import de.hdm.team7.client.ui.views.CreateComponentAssemblyView;
import de.hdm.team7.client.ui.views.CreateComponentView;
import de.hdm.team7.client.ui.views.CreateEndProductView;
import de.hdm.team7.client.ui.views.EditBillOfMaterialView;
import de.hdm.team7.client.ui.views.EditComponentAssemblyView;
import de.hdm.team7.client.ui.views.EditComponentView;
import de.hdm.team7.client.ui.views.EditEndProductView;
import de.hdm.team7.shared.BOMAdministrationAsync;

public class AppController implements Presenter {

	private final HandlerManager eventBus;
	private final BOMAdministrationAsync bom;
	private Presenter createComponentPresenter;
	private Presenter createComponentAssemblyPresenter;
	private Presenter createEndProductPresenter;
	private Presenter createBillOfMaterialPresenter;
	private Presenter editComponentPresenter;
	private Presenter editComponentAssemblyPresenter;
	private Presenter editEndProductPresenter;
	private Presenter editBillOfMaterialPresenter;
	private DockPanel container;

	public AppController(BOMAdministrationAsync bom, HandlerManager eventBus) {
		this.eventBus = eventBus;
		this.bom = bom;
		bind();
	}

	private void bind() {

		eventBus.addHandler(AddComponentEvent.TYPE,
				new AddComponentEventHandler() {
					public void onAddComponent(AddComponentEvent event) {
						doAddComponent();
					}
				});
		
		eventBus.addHandler(CreateComponentCancelledEvent.TYPE,
				new CreateComponentCancelledEventHandler() {
					public void onCreateComponentCancelled(
							CreateComponentCancelledEvent event) {
						doCreateComponentCancelled();
					}
				});

		eventBus.addHandler(EditComponentEvent.TYPE,
				new EditComponentEventHandler() {
					public void onEditComponent(EditComponentEvent event) {
						doEditComponent(event.getId());
					}
				});

		eventBus.addHandler(EditComponentCancelledEvent.TYPE,
				new EditComponentCancelledEventHandler() {
					public void onEditComponentCancelled(
							EditComponentCancelledEvent event) {
						doEditComponentCancelled();
					}
				});

		eventBus.addHandler(ComponentUpdatedEvent.TYPE,
				new ComponentUpdatedEventHandler() {
					public void onComponentUpdated(ComponentUpdatedEvent event) {
						doComponentUpdated();
					}
				});
		
		eventBus.addHandler(ComponentDeletedEvent.TYPE,
				new ComponentDeletedEventHandler() {
					public void onComponentDeleted(ComponentDeletedEvent event) {
						doComponentDeleted();
					}
				});
		
		////////////////////////////////////////////////////////////////////////////////
		
		eventBus.addHandler(AddComponentAssemblyEvent.TYPE,
				new AddComponentAssemblyEventHandler() {
					public void onAddComponentAssembly(AddComponentAssemblyEvent event) {
						doAddComponentAssembly();
					}
				});
		
		eventBus.addHandler(CreateComponentAssemblyCancelledEvent.TYPE,
				new CreateComponentAssemblyCancelledEventHandler() {
					public void onCreateComponentAssemblyCancelled(
							CreateComponentAssemblyCancelledEvent event) {
						doCreateComponentAssemblyCancelled();
					}
				});

		eventBus.addHandler(EditComponentAssemblyEvent.TYPE,
				new EditComponentAssemblyEventHandler() {
					public void onEditComponentAssembly(EditComponentAssemblyEvent event) {
						doEditComponentAssembly(event.getId());
					}
				});

		eventBus.addHandler(EditComponentAssemblyCancelledEvent.TYPE,
				new EditComponentAssemblyCancelledEventHandler() {
					public void onEditComponentAssemblyCancelled(
							EditComponentAssemblyCancelledEvent event) {
						doEditComponentAssemblyCancelled();
					}
				});

		eventBus.addHandler(ComponentAssemblyUpdatedEvent.TYPE,
				new ComponentAssemblyUpdatedEventHandler() {
					public void onComponentAssemblyUpdated(ComponentAssemblyUpdatedEvent event) {
						doComponentAssemblyUpdated();
					}
				});
		
		eventBus.addHandler(ComponentAssemblyDeletedEvent.TYPE,
				new ComponentAssemblyDeletedEventHandler() {
					public void onComponentAssemblyDeleted(ComponentAssemblyDeletedEvent event) {
						doComponentAssemblyDeleted();
					}
				});
		
		////////////////////////////////////////////////////////////////////////////////////////////////
		
		eventBus.addHandler(AddEndProductEvent.TYPE,
				new AddEndProductEventHandler() {
					public void onAddEndProduct(AddEndProductEvent event) {
						doAddEndProduct();
					}
				});
		
		eventBus.addHandler(CreateEndProductCancelledEvent.TYPE,
				new CreateEndProductCancelledEventHandler() {
					public void onCreateEndProductCancelled(
							CreateEndProductCancelledEvent event) {
						doCreateEndProductCancelled();
					}
				});

		eventBus.addHandler(EditEndProductEvent.TYPE,
				new EditEndProductEventHandler() {
					public void onEditEndProduct(EditEndProductEvent event) {
						doEditEndProduct(event.getId());
					}
				});

		eventBus.addHandler(EditEndProductCancelledEvent.TYPE,
				new EditEndProductCancelledEventHandler() {
					public void onEditEndProductCancelled(
							EditEndProductCancelledEvent event) {
						doEditEndProductCancelled();
					}
				});

		eventBus.addHandler(EndProductUpdatedEvent.TYPE,
				new EndProductUpdatedEventHandler() {
					public void onEndProductUpdated(EndProductUpdatedEvent event) {
						doEndProductUpdated();
					}
				});
		
		eventBus.addHandler(EndProductDeletedEvent.TYPE,
				new EndProductDeletedEventHandler() {
					public void onEndProductDeleted(EndProductDeletedEvent event) {
						doEndProductDeleted();
					}
				});
		
		//////////////////////////////////////////////////////////////////////////////////////
		
		eventBus.addHandler(AddBillOfMaterialEvent.TYPE,
				new AddBillOfMaterialEventHandler() {
					public void onAddBillOfMaterial(AddBillOfMaterialEvent event) {
						doAddBillOfMaterial();
					}
				});
		
		eventBus.addHandler(CreateBillOfMaterialCancelledEvent.TYPE,
				new CreateBillOfMaterialCancelledEventHandler() {
					public void onCreateBillOfMaterialCancelled(
							CreateBillOfMaterialCancelledEvent event) {
						doCreateBillOfMaterialCancelled();
					}
				});

		eventBus.addHandler(EditBillOfMaterialEvent.TYPE,
				new EditBillOfMaterialEventHandler() {
					public void onEditBillOfMaterial(EditBillOfMaterialEvent event) {
						doEditBillOfMaterial(event.getId());
					}
				});

		eventBus.addHandler(EditBillOfMaterialCancelledEvent.TYPE,
				new EditBillOfMaterialCancelledEventHandler() {
					public void onEditBillOfMaterialCancelled(
							EditBillOfMaterialCancelledEvent event) {
						doEditBillOfMaterialCancelled();
					}
				});

		eventBus.addHandler(BillOfMaterialUpdatedEvent.TYPE,
				new BillOfMaterialUpdatedEventHandler() {
					public void onBillOfMaterialUpdated(BillOfMaterialUpdatedEvent event) {
						doBillOfMaterialUpdated();
					}
				});
		
		eventBus.addHandler(BillOfMaterialDeletedEvent.TYPE,
				new BillOfMaterialDeletedEventHandler() {
					public void onBillOfMaterialDeleted(BillOfMaterialDeletedEvent event) {
						doBillOfMaterialDeleted();
					}
				});
	}

	protected void doBillOfMaterialDeleted() {
		//TODO: container.go() zum Refreshen erneut ausführen!!!
	}

	protected void doBillOfMaterialUpdated() {
		//TODO: container.go() zum Refreshen erneut ausführen!!!
	}

	protected void doEditBillOfMaterialCancelled() {
		//TODO: container.splitLayout.clear() ausführen!!!
	}

	protected void doEditBillOfMaterial(String id) {
//		this.editBillOfMaterialPresenter = new EditBillOfMaterialPresenter(bom, eventBus, new EditBillOfMaterialView(), id);
	    editBillOfMaterialPresenter.go(container);
	}

	protected void doCreateBillOfMaterialCancelled() {
		//TODO: container.splitLayout.clear() ausführen!!!
	}

	protected void doAddBillOfMaterial() {
//		this.createBillOfMaterialPresenter = new CreateBillOfMaterialPresenter(bom, eventBus, new CreateBillOfMaterialView());
	    createBillOfMaterialPresenter.go(container);
	}

	protected void doEndProductDeleted() {
		//TODO: container.go() zum Refreshen erneut ausführen!!!
	}

	protected void doEndProductUpdated() {
		//TODO: container.go() zum Refreshen erneut ausführen!!!
	}

	protected void doEditEndProductCancelled() {
		//TODO: container.splitLayout.clear() ausführen!!!
	}

	protected void doEditEndProduct(int id) {
//		this.editEndProductPresenter = new EditEndProductPresenter(bom, eventBus, new EditEndProductView(), id);
	    editEndProductPresenter.go(container);
	}

	protected void doCreateEndProductCancelled() {
		//TODO: container.splitLayout.clear() ausführen!!!
	}

	protected void doAddEndProduct() {
//		this.createEndProductPresenter = new CreateEndProductPresenter(bom, eventBus, new CreateEndProductView());
	    createEndProductPresenter.go(container);
	}

	protected void doComponentAssemblyDeleted() {
		//TODO: container.go() zum Refreshen erneut ausführen!!!
	}

	protected void doComponentAssemblyUpdated() {
		//TODO: container.go() zum Refreshen erneut ausführen!!!
	}

	protected void doEditComponentAssemblyCancelled() {
		//TODO: container.splitLayout.clear() ausführen!!!
	}

	protected void doEditComponentAssembly(int id) {
//		this.editComponentAssemblyPresenter = new EditComponentAssemblyPresenter(bom, eventBus, new EditComponentAssemblyView(), id);
	    editComponentAssemblyPresenter.go(container);
	}

	protected void doCreateComponentAssemblyCancelled() {
		//TODO: container.splitLayout.clear() ausführen!!!
	}

	protected void doAddComponentAssembly() {
//		this.createComponentAssemblyPresenter = new CreateComponentAssemblyPresenter(bom, eventBus, new CreateComponentAssemblyView());
	    createComponentAssemblyPresenter.go(container);
	}

	protected void doComponentDeleted() {
		//TODO: container.go() zum Refreshen erneut ausführen!!!
	}

	protected void doComponentUpdated() {
		//TODO: container.go() zum Refreshen erneut ausführen!!!
	}

	protected void doEditComponentCancelled() {
		//TODO: container.splitLayout.clear() ausführen!!!
	}

	protected void doEditComponent(int id) {
		this.editComponentPresenter = new EditComponentPresenter(bom, eventBus, new EditComponentView(), id);
	    editComponentPresenter.go(container);
	}

	protected void doCreateComponentCancelled() {
		//TODO: container.splitLayout.clear() ausführen!!!
	}

	protected void doAddComponent() {
		this.createComponentPresenter = new CreateComponentPresenter(bom, eventBus, new CreateComponentView());
	    createComponentPresenter.go(container);
	}

	public void go(final DockPanel container) {
		this.container = container;
		Presenter businessObjectTree = new BusinessObjectTreePresenter(bom, eventBus, new BusinessObjectTreeView());
		businessObjectTree.go(container);
		Presenter createComponentPresenter = new CreateComponentPresenter(bom, eventBus, new CreateComponentView());
		createComponentPresenter.go(container);
	}

}
