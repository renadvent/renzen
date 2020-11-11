export function errorState(state, action) {
  //test alert for error
  alert(JSON.stringify(action.payload, null, 5));

  return {
    ...state,
    errors: action.payload,
  };
}
