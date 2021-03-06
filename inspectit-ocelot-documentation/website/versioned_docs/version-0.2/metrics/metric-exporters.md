---
id: version-0.2-metric-exporters
title: Metrics Exporters
original_id: metric-exporters
---

Metrics exporters are responsible for passing the recorded metrics to a metric storage.
They can implement a push approach where metrics are sent to a collector or a pull approach where metrics are scraped by an external system.

If an exporter supports run-time updates it means that it can be enabled/disabled during the run-time or that any property related to the exporter can be changed.
This way you can, for example, change the endpoint where exporter pushes the metrics without a need to restart the application.
In order to use run-time updates, you must enable one of the [externalized configuration methods](configuration/external-configuration-sources) that support dynamic updates.

inspectIT Ocelot current supports the following OpenCensus metrics exporters:

|Exporter |Supports run-time updates| Push / Pull |Enabled by default
|---|---|---|---|
|[Prometheus Exporter](#prometheus-exporter)|Yes|Pull|Yes

## Prometheus Exporter

Prometheus exporter exposes the metrics in Prometheus format and is the default metrics exporter set up by inspectIT Ocelot.
When enabled, inspectIT starts a Prometheus HTTP server in parallel with your application.
The server is by default started on the port `8888` and metrics can then be accessed by visiting http://localhost:8888/metrics.

|Property |Default| Description
|---|---|---|
|`inspectit.exporters.metrics.prometheus.enabled`|`true`|If true, the inspectIT Ocelot agent will try to start the Prometheus metrics exporter and Prometheus HTTP server.
|`inspectit.exporters.metrics.prometheus.host`|`0.0.0.0`|The hostname or network address to which the Prometheus HTTP server should bind.
|`inspectit.exporters.metrics.prometheus.port`|`8888`|The port the Prometheus HTTP server should use.


> Don't forget to check [the official OpenCensus Prometheus exporter documentation](https://opencensus.io/exporters/supported-exporters/java/prometheus/).

## OpenCensus Agent Metrics Exporter

Metrics can be additionally exported to the [OpenCensus Agent](https://opencensus.io/service/components/agent/).
When enabled, all metrics are send via gRCP to the OpenCensus Agent. By default, the exporter is enabled, but the agent address is set to `null`.

|Property |Default| Description
|---|---|---|
|`inspectit.exporters.metrics.open-census-agent.enabled`|`true`|If true, the agent will try to start the OpenCensus Agent Metrics exporter.
|`inspectit.exporters.metrics.open-census-agent.address`|`null`|Address of the open-census agent (e.g. localhost:1234).
|`inspectit.exporters.metrics.open-census-agent.use-insecure`|`false`|If true, SSL is disabled.
|`inspectit.exporters.metrics.open-census-agent.service-name`|refers to `inspectit.service-name`|The service-name which will be used to publish the metrics.
|`inspectit.exporters.metrics.open-census-agent.reconnection-period`|`5`|The time at which the exporter tries to reconnect to the OpenCensus agent.
|`inspectit.exporters.metrics.open-census-agent.export-interval`|refers to `inspectit.metrics.frequency`|The export interval of the metrics.

> Don't forget to check [the official OpenCensus Agent exporter documentation](https://opencensus.io/exporters/supported-exporters/java/ocagent/).