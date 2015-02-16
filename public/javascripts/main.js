requirejs.config({
  baseUrl: 'assets/javascripts',
  paths: {
    lib: 'assets/lib'
  }
});

requirejs(['game/Test'], function(Test) {
  Test.test();
});
