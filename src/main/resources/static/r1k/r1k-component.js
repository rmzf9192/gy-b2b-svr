(function(root) {
  const R = root.R;

  function render(component, $container) {
    component.then
      ? component.then(c => ReactDOM.render(c, $container))
      : ReactDOM.render(component, $container);
  }

  function fallbackHandle(err, url, $container) {
    if (err.status && err.status >= 400) {
      const $message = React.createElement(rsuite.Message, {
        type: 'error',
        description: `[EDP-${err.status}] Load resource FAILED: ${url}`,
      });
      render($message, $container);
    } else {
      console.error('[EDP-R1K] run script FAILED:', url, err);
    }
  }

  root.RR = function(pageUrl, $element, force) {
    const $container = $element || root.document.getElementById('root');
    const componentUrl = './src' + (pageUrl || '') + '/index.js';
    console.log('[EDP-R1K] LOAD...', componentUrl);
    R(componentUrl, function(err, componentOrUrl) {
      if (err) {
        fallbackHandle(err /*req*/, componentOrUrl /*url*/, $container);
      } else {
        render(componentOrUrl /*component*/, $container);
      }
    });
  };

  // Disable cache of modules in `src`
  const NO_CACHE_URL = /\/src\//;
  R.resolvePost = function(modules, module) {
    console.log('[EDP-R1K] LOADED:', module.l);
    if (NO_CACHE_URL.test(module.l)) {
      modules[module.l] = void 0;
      console.log('[EDP-R1K] UNCACHED:', module.l);
    }
  };

  console.log('[EDP-R1K] global function `RR` registered.');
})(this);
