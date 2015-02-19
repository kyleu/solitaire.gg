define(function() {
  var StatusPanel = function(game) {
    this.connectionStatus = new Phaser.Text(game, 20, 20, "Starting", { font: "16px Helvetica", fill: "#ffffff" });
    this.latency = new Phaser.Text(game, 20, 40, "---", { font: "16px Helvetica", fill: "#ffffff" });
    this.fps = new Phaser.Text(game, 20, 60, "", { font: "16px Helvetica", fill: "#ffffff" });
    this.version = new Phaser.Text(game, 20, 80, "v0.0", { font: "16px Helvetica", fill: "#ffffff" });

    this.group = new Phaser.Group(game, null, "status-panel", true);

    this.group.add(this.connectionStatus);
    this.group.add(this.latency);
    this.group.add(this.fps);
    this.group.add(this.version);
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
