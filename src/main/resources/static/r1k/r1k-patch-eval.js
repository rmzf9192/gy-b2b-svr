(function patch(root) {
  const babelOps = { presets: ['es2015', 'react'] };
  const globalEval = root.eval;
  root.eval = function(code) {
    return globalEval(Babel.transform(code, babelOps).code);
  };
  console.log('[EDP-R1K] global function `eval` patched for JSX tranformation by Babel.');
})(this);
