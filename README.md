# AOS Appium S01 - Browse & Add to Cart

Selenium/Appium mobile automation demo for **OpenText Functional Testing Lab (FT Lab) / Digital Lab**, with execution from **IntelliJ IDEA/Maven** or **GitHub Actions** and optional **OpenText Core SDP** integration.

## Test Flow

**Category Page → Product → Add To Cart → Back to Home**

## Technology

- Java 17
- Maven
- Selenium / Appium
- OpenText FT Lab / Digital Lab
- GitHub Actions
- OpenText Core SDP

## FT Lab Configuration

Credentials are not stored in the repository. The Java test reads the following environment variables:

| Variable | Purpose |
|---|---|
| `FTLAB_SERVER` | FT Lab / Digital Lab server URL |
| `FTLAB_CLIENT_ID` | OAuth client ID |
| `FTLAB_CLIENT_SECRET` | OAuth client secret |
| `FTLAB_TENANT_ID` | Tenant ID |

### Local execution

Configure these variables on the local machine, restart IntelliJ IDEA so it inherits them, and run:

```bash
mvn test
```

### GitHub Actions

Configure:

**Repository variable**
- `FTLAB_SERVER`

**Repository secrets**
- `FTLAB_CLIENT_ID`
- `FTLAB_CLIENT_SECRET`
- `FTLAB_TENANT_ID`

Run the **FT Lab Appium Mobile Test** workflow manually from GitHub Actions, or trigger it from Core SDP.

## Core SDP Integration

The repository uses two workflows:

- `FT Lab Appium Mobile Test` - executes the Maven/Appium test in FT Lab.
- `Core SDP Integration With GitHub Actions` - publishes Core SDP-triggered executions and test results to Core SDP.

The Appium workflow accepts the Core SDP automated-test parameters:

- `testsToRun`
- `suiteId`
- `suiteRunId`
- `executionId`

Manual GitHub runs default to `executionId=0`. The Core SDP integration workflow ignores those runs, allowing the Appium test to be executed independently without publishing it to Core SDP.

The Core SDP integration uses:

- `ubuntu-latest` GitHub-hosted runner
- OpenText `opentext/sdp-github-actions-integration`
- Selenium/TestNG test reporting
- Maven Surefire XML results from `target/surefire-reports`

Core SDP credentials are stored as GitHub repository secrets:

- `ALM_OCTANE_CLIENT_ID`
- `ALM_OCTANE_CLIENT_SECRET`

For Core SDP to initiate tests, configure a GitHub App credential in Core SDP and associate it with the GitHub CI server.

## Repository Security

Do not commit:

- `.idea/`
- `target/`
- `.env` files
- FT Lab OAuth credentials
- Core SDP client credentials

Keep all credentials in local environment variables or GitHub Actions Secrets.
