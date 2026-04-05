# MediCore Hospital Management System - Docker Deployment

This document describes how to deploy the MediCore Hospital Management System using Docker Compose.

## Prerequisites

- Docker Engine 20.10+
- Docker Compose 2.0+
- At least 4GB RAM available for containers

## Architecture

```
┌─────────────────────────────────────────────────────────────┐
│                      Docker Network                         │
│                       (hms-network)                         │
├─────────────────────────────────────────────────────────────┤
│                                                             │
│   ┌─────────────┐    ┌─────────────┐    ┌─────────────┐  │
│   │   Frontend   │───▶│ API Gateway │───▶│  Patient    │  │
│   │   (React)    │    │  (8080)     │    │  Service    │  │
│   │   :3000      │    │             │    │  (8081)     │  │
│   └─────────────┘    └─────────────┘    └─────────────┘  │
│                              │                              │
│                              ▼                              │
│         ┌─────────┬─────────┼─────────┬─────────┐        │
│         ▼         ▼         ▼         ▼         ▼        │
│    ┌─────────┐┌─────────┐┌─────────┐┌─────────┐┌─────────┐
│    │ Doctor  ││Appoint- ││Pharmacy ││ Billing ││Lab Test │
│    │Service  ││ ment    ││ Service ││ Service ││ Service │
│    │(8082)   ││(8083)   ││ (8084)  ││ (8085)  ││ (8086)  │
│    └─────────┘└─────────┘└─────────┘└─────────┘└─────────┘
│                              │                              │
│                              ▼                              │
│                      ┌─────────────┐                       │
│                      │  MongoDB    │                       │
│                      │   :27017    │                       │
│                      └─────────────┘                       │
└─────────────────────────────────────────────────────────────┘
```

## Services

| Service | Port | Description |
|---------|------|-------------|
| Frontend | 3000 | React web application |
| API Gateway | 8080 | Spring Cloud Gateway routing |
| Patient Service | 8081 | Patient management |
| Doctor Service | 8082 | Doctor management |
| Appointment Service | 8083 | Appointment scheduling |
| Pharmacy Service | 8084 | Medicine/inventory management |
| Billing Service | 8085 | Invoice/billing management |
| Lab Test Service | 8086 | Laboratory test management |
| MongoDB | 27017 | Document database |

## Quick Start

### 1. Build and Start All Services

```bash
# From the project root directory
docker-compose up --build -d
```

### 2. View Logs

```bash
# All services
docker-compose logs -f

# Specific service
docker-compose logs -f api-gateway
docker-compose logs -f frontend
```

### 3. Verify Services

```bash
# Check container status
docker-compose ps

# Check service health
curl http://localhost:8080/actuator/health  # API Gateway
curl http://localhost:8081/actuator/health  # Patient Service
```

## Access Points

| Service | URL |
|---------|-----|
| Frontend | http://localhost:3000 |
| API Gateway | http://localhost:8080 |
| Swagger UI (Aggregated) | http://localhost:8080/swagger-ui.html |
| Patient Service | http://localhost:8081/swagger-ui.html |
| Doctor Service | http://localhost:8082/swagger-ui.html |
| Appointment Service | http://localhost:8083/swagger-ui.html |
| Pharmacy Service | http://localhost:8084/swagger-ui.html |
| Billing Service | http://localhost:8085/swagger-ui.html |
| Lab Test Service | http://localhost:8086/swagger-ui.html |

## Common Commands

### Stop Services
```bash
docker-compose down
```

### Stop and Remove Volumes (Clean Start)
```bash
docker-compose down -v
```

### Rebuild Single Service
```bash
docker-compose up --build -d <service-name>
# Example
docker-compose up --build -d patient-service
```

### Scale (Development Only)
```bash
docker-compose up -d --scale patient-service=2
```

## Troubleshooting

### Services Fail to Start
```bash
# Check MongoDB is healthy
docker-compose ps mongodb

# Check logs for errors
docker-compose logs mongodb
docker-compose logs <failing-service>
```

### Frontend Cannot Connect to API
- Ensure the frontend is using the correct gateway URL
- Check nginx proxy configuration in `hospital-frontend/nginx.conf`
- Verify services are on the same Docker network

### Memory Issues
```bash
# Check container resource usage
docker stats

# Adjust memory limits in docker-compose.yml if needed
```

### Clear All Data
```bash
docker-compose down -v
docker volume rm hms_mongodb_data
```

## Environment Variables

The following environment variables can be configured:

| Variable | Default | Description |
|----------|---------|-------------|
| `SPRING_PROFILES_ACTIVE` | `docker` | Spring profile |
| `SERVER_PORT` | Service-specific | Container port |
| `BUILD_VERSION` | `1.0.0` | Build version |

## Development

### Running with Hot Reload (Frontend)
```bash
# For frontend development, run outside Docker
cd hospital-frontend
npm install --legacy-peer-deps
npm start

# Backend services still run in Docker
docker-compose up -d
```

### Viewing MongoDB Data
```bash
docker exec -it hms-mongodb mongosh
use patientdb
db.patients.find()
```

## Production Considerations

For production deployment:
1. Remove exposed ports for backend services
2. Use external MongoDB with authentication
3. Configure SSL/TLS termination
4. Set up proper logging and monitoring
5. Use Docker secrets for sensitive data
6. Implement health checks for all services

## License

This project is for educational purposes as part of the IT4020 Modern Topics in IT course.
