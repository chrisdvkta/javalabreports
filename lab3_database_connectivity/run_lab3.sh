#!/usr/bin/env bash
set -euo pipefail

if [[ $# -lt 1 ]]; then
  echo "Usage: ./run_lab3.sh <ClassName> [DB_URL] [DB_USER] [DB_PASS]"
  echo "Example: ./run_lab3.sh Lab3Q02UpdatableResultSet"
  exit 2
fi

class_name="$1"
db_url="${2:-${DB_URL:-jdbc:mysql://localhost:${AJP_MYSQL_PORT:-3310}/ajp}}"
db_user="${3:-${DB_USER:-ajp}}"
db_pass="${4:-${DB_PASS:-ajp}}"

script_dir="$(cd "$(dirname "${BASH_SOURCE[0]}")" && pwd)"
cd "$script_dir"

mkdir -p lib

if ! ls lib/*.jar >/dev/null 2>&1; then
  echo "Downloading MySQL Connector/J into lab3_database_connectivity/lib/ ..."
  mvn -q -f pom.xml dependency:copy-dependencies -DincludeScope=runtime -DoutputDirectory=lib
fi

echo "Compiling..."
javac -cp "lib/*" *.java

echo "Running..."
java -cp ".:lib/*" "$class_name" "$db_url" "$db_user" "$db_pass"

