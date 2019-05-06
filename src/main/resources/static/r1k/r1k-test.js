function assert(expected, actual, message) {
  expected !== actual && console.error('[R1K] assertion:', message);
}
function assertR1kModule(r1kModule) {
  const { l, t, f, g } = r1kModule;
  assert('', l, '`l` is empty');
  assert('', g, '`g` is empty');
  assert(aModule, f, '`f` is the module');
  console.log('[R1K] `t` is content of the module:', t);
}

function aModule(require, module, r1kModule) {
  console.log('[R1K] call:', arguments);
  assertR1kModule(r1kModule);
  const hello = 'world';
  module.exports = hello;
}

new Promise(function(resolve) {
  console.log('[R1K] === R(fnModule, fnCallback) ===');
  R(aModule, function(err, module) {
    console.log('[R1K] callback:', arguments);
    const hello = module.exports;
    console.log('[R1K] hello', hello);
    resolve();
  });
}).then(() => {
  console.log('[R1K] === R(modulePath, fnCallback) ===');
  R('./r1k/r1k-test-module', function(err, hello) {
    console.log('[R1K] callback:', arguments);
    console.log('[R1K] hello', hello);
  });
});
