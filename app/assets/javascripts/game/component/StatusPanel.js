define(function() {
  var StatusPanel = function(game) {
    this.connectionStatus = new Phaser.Text(game, 20, 20, "Starting", { font: "16px Helvetica", fill: "#ffffff" });
    this.latency = new Phaser.Text(game, 20, 40, "---", { font: "16px Helvetica", fill: "#ffffff" });
    this.fps = new Phaser.Text(game, 20, 60, "", { font: "16px Helvetica", fill: "#ffffff" });
    this.version = new Phaser.Text(game, 20, 80, "v0.0", { font: "16px Helvetica", fill: "#ffffff" });

    game.stage.addChild(this.connectionStatus);
    game.stage.addChild(this.latency);
    game.stage.addChild(this.fps);
    game.stage.addChild(this.version);
  };

  StatusPanel.prototype.connected = function() {
    this.connectionStatus.text = "Connected";
  };

  StatusPanel.prototype.disconnected = function() {
    this.connectionStatus.text = "Disconnected";
  };

  StatusPanel.prototype.setLatency = function(latencyMs) {
    this.latency.text = latencyMs + "ms";
  };

  StatusPanel.prototype.setFps = function(fps) {
    this.fps.text = fps + " fps";
  };

  StatusPanel.prototype.setVersion = function(version) {
    this.version.text = "v" + version;
  };

  return StatusPanel;
});
