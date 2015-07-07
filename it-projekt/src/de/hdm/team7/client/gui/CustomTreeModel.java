package de.hdm.team7.client.gui;

import java.util.ArrayList;

import com.google.gwt.cell.client.AbstractCell;
import com.google.gwt.cell.client.Cell;
import com.google.gwt.safehtml.shared.SafeHtmlBuilder;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.view.client.ListDataProvider;
import com.google.gwt.view.client.TreeViewModel;

import de.hdm.team7.client.BauteilCell;
import de.hdm.team7.client.ClientEinstellungen;
import de.hdm.team7.client.EnderzeugnisCell;
import de.hdm.team7.shared.StuecklistenVerwaltungAsync;
import de.hdm.team7.shared.geschaeftsobjekte.Baugruppe;
import de.hdm.team7.shared.geschaeftsobjekte.Bauteil;
import de.hdm.team7.shared.geschaeftsobjekte.Enderzeugnis;
import de.hdm.team7.shared.geschaeftsobjekte.Stueckliste;
import de.hdm.team7.shared.geschaeftsobjekte.ZuordnungBGBT;
import de.hdm.team7.shared.geschaeftsobjekte.ZuordnungEEBG;
import de.hdm.team7.shared.geschaeftsobjekte.ZuordnungEEBT;

class CustomTreeModel implements TreeViewModel {
	
	StuecklistenVerwaltungAsync stuecklistenVerwaltung = ClientEinstellungen
			.getStuecklistenVerwaltung();
	
	public <T> NodeInfo<?> getNodeInfo(T value) {
			if (value == null) {
				final ListDataProvider<Enderzeugnis> dataProvider = new ListDataProvider<Enderzeugnis>();
				
				stuecklistenVerwaltung.holeAlleEnderzeugnisse(new AsyncCallback<ArrayList<Enderzeugnis>>() {
					@Override
					public void onFailure(Throwable caught) {
						// TODO Auto-generated method stub	
					}

					@Override
					public void onSuccess(ArrayList<Enderzeugnis> result) {
						for (Enderzeugnis b : result){
							dataProvider.getList().add(b);
						}
				
					}
				});
		
				EnderzeugnisCell cell = new EnderzeugnisCell();
		
				return new DefaultNodeInfo<Enderzeugnis>(dataProvider, cell);
			} else if (value instanceof Enderzeugnis) {
				 final ListDataProvider<Bauteil> dataProvider = new ListDataProvider<Bauteil>();
				 
				 stuecklistenVerwaltung.holeKinderBauteileVonEnderzeugnis((Enderzeugnis) value, new AsyncCallback<ArrayList<Bauteil>>() {
					@Override
					public void onFailure(Throwable caught) {
						// TODO Auto-generated method stub	
					}

					@Override
					public void onSuccess(ArrayList<Bauteil> result) {
						for (Bauteil b : result){
							dataProvider.getList().add(b);
						}
					}
				 });
				 
//				 stuecklistenVerwaltung.holeKinderBaugruppenVonEnderzeugnis(value, new AsyncCallback<Enderzeugnis>() {
//						@Override
//						public void onFailure(Throwable caught) {
//							// TODO Auto-generated method stub	
//						}
//
//						@Override
//						public void onSuccess(ArrayList<ZuordnungEEBG> result) {
//							for (Baugruppe b : result){
//								dataProvider.getList().add(b);
//							}
//						}
//					 });
//				 
//				 Cell<ZuordnungEEBT> cell = new AbstractCell<ZuordnungEEBT>() {
//			          @Override
//			          public void render(Context context, ZuordnungEEBT value, SafeHtmlBuilder sb) {
//			            if (value != null) {
////			              sb.appendEscaped(value.getId());
//			            }
//			          }
//			        };
				 BauteilCell cell = new BauteilCell();
				 return new DefaultNodeInfo<Bauteil>(dataProvider, cell);
			}  else if (value instanceof Bauteil) {
				 final ListDataProvider<Bauteil> dataProvider = new ListDataProvider<Bauteil>();
				 
				 stuecklistenVerwaltung.holeAlleBauteile(new AsyncCallback<ArrayList<Bauteil>>() {
					@Override
					public void onFailure(Throwable caught) {
						// TODO Auto-generated method stub	
					}

					@Override
					public void onSuccess(ArrayList<Bauteil> result) {
						for (Bauteil b : result){
							dataProvider.getList().add(b);
						}
					
					}
				 });
			} else if (value instanceof Baugruppe) {
				
			}
	
			
			
      return null;
	}
	
	
//    public <T> NodeInfo<?> getNodeInfo(T value) {
//      if (value == null) {
//        ListDataProvider<Composer> dataProvider = new ListDataProvider<CellTreeExample2.Composer>(
//            composers);
//        // Create a cell to display a composer.
//        Cell<Composer> cell = new AbstractCell<Composer>() {
//          @Override
//          public void render(Context context, Composer value, SafeHtmlBuilder sb) {
//            if (value != null) {
//              sb.appendEscaped(value.getName());
//            }
//          }
//        };
//        // Return a node info that pairs the data provider and the cell.
//        return new DefaultNodeInfo<Bauteil>(dataProvider, new BauteilCell());
//      } else if (value instanceof Composer) {
//        // LEVEL 1.
//        // We want the children of the composer. Return the playlists.
//        ListDataProvider<Playlist> dataProvider = new ListDataProvider<Playlist>(
//            ((Composer) value).getPlaylists());
//        Cell<Playlist> cell = new AbstractCell<Playlist>() {
//          @Override
//          public void render(Context context, Playlist value, SafeHtmlBuilder sb) {
//            if (value != null) {
//              sb.appendEscaped(value.getName());
//            }
//          }
//        };
//        return new DefaultNodeInfo<Playlist>(dataProvider, cell);
//      } else if (value instanceof Playlist) {
//        // LEVEL 2 - LEAF.
//        // We want the children of the playlist. Return the songs.
//        ListDataProvider<String> dataProvider = new ListDataProvider<String>(
//            ((Playlist) value).getSongs());
//        // Use the shared selection model.
//        return new DefaultNodeInfo<String>(dataProvider, new TextCell(),
//            selectionModel, null);
//      }
//      return null;
//    }
	
    public boolean isLeaf(Object value) {
      if (value instanceof String) {
        return true;
      }
      return false;
    }

}
