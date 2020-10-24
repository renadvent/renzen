export function TabPane_StateToProps(state) {
  return {
    open: state.reducer.tabs.open,
  };
}
