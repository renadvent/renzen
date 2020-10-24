import { connect } from "react-redux";
import * as store from "../actions/Store_Actions";

const mapStateToProps = (state) => {
  return {
    open: state.reducer.tabs.open,
  };
};

const mapDispatchToProps = (dispatch) => {
  return {
    DISPATCH_init: () => dispatch(store.DISPATCH_init()),
  };
};

function ReactComponent() {}

export default connect(mapStateToProps, mapDispatchToProps)(ReactComponent);
