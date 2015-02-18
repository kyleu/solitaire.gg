define(function() {
  var StatusPanel = function(game) {
    this.connectionStatus = new Phaser.Text(game, 20, 20, "Starting", { font: "12px Helvetica", fill: "#ffffff" });
    this.latency = new Phaser.Text(game, 20, 40, "---", { font: "12px Helvetica", fill: "#ffffff" });
    this.fps = new Phaser.Text(game, 20, 60, "0 fps", { font: "12px Helvetica", fill: "#ffffff" });

    game.stage.addChild(this.connectionStatus);
    game.stage.addChild(this.latency);
    game.stage.addChild(this.fps);
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

  return StatusPanel;
});
