workflow "Simple build" {
  on = "push"
  resolves = ["Run maven"]
}

action "Run maven" {
  uses = "LucaFeger/action-maven-cli@765e218a50f02a12a7596dc9e7321fc385888a27"
  args = "clean install -DskipTests"
}
