import Axios from "axios";

//single dispatch
export function Dispatch_Action() {
  return async (dispatch, getState) => {
    let res = await Axios.get("/");
    dispatch({
      type: "Action",
      data: "data",
    });
  };
}

//dispatch when multiple dispatches finish
function loadPosts() {}
function loadProfile() {}
function updateDone() {}

export function Dispatch_Action() {
  return async (dispatch, getState) => {
    let res = await Axios.get("/");

    await Promise.all([
      dispatch(loadPosts()), // <-- async dispatch chaining in action
      dispatch(loadProfile()),
    ]);

    return dispatch(updateDone());
  };
}
