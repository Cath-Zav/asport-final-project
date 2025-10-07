# TEST AUTOMATION FRAMEWORK
## [asport.by](https://asport.by/)
### TAF for automation of UI and API testing
## ✨ Features

- **UI tests** for core user flows:
    - Login (positive & negative scenarios)
    - Search (valid queries, long gibberish strings, short/empty queries)
    - Shopping Cart (add/remove items, change quantities, total recalculation)
- **API tests** (authentication, search endpoints)
- Rich **Allure Reports** with steps, logs, and screenshots
- Local and CI execution (**Jenkins / GitHub Actions**)
- Dependency and build management via **Maven**

---

## 🧰 Tech Stack

- **Java** — programming language
- **JUnit 5** — test framework
- **Selenium** — UI automation
- **REST Assured** — API testing
- **Maven** — build & dependency management
- **Allure Report** — reporting & visualization
- **Jenkins / GitHub Actions** — CI/CD integration
- **GitHub** — source control & collaboration

---

## 📁 Project Structure (recommended)

> Actual structure may differ slightly — this is a guideline for navigation and maintenance.

asport-final-project/
├─ pom.xml
├─ src
│ ├─ main
│ │ └─ java
│ │ ├─ config/ # environment & config files
│ │ ├─ driver/ # WebDriver setup
│ │ ├─ pages/ # Page Object models
│ │ ├─ api/ # API clients and models
│ │ └─ utils/ # helpers, waits, utilities
│ └─ test
│ └─ java
│ ├─ ui/ # UI test cases
│ ├─ api/ # API test cases
│ └─ data/ # test data & providers
└─ README.md

## 🧭 Test Strategy (summary)

UI: login (positive/negative), search behavior (long gibberish → no results, short/empty → results), cart operations (add/remove/quantity/total).

API: authentication, search endpoints (valid/invalid inputs, response schema & status codes).

Approach: Black Box — no access to system documentation, so both behaviors (search logic) are included in coverage.

## 🙌 Acknowledgements

Thanks to mentors and peers for feedback and support.
Special thanks for insights on search behavior testing and reporting with Allure.