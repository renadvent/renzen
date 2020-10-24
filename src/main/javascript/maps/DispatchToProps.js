import * as store from "../actions/Store_Actions";

export const TabPane_mapDispatchToProps = (dispatch) => {
  return {
    DISPATCH_init: () => dispatch(store.DISPATCH_init()),
  };
};
