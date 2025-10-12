import requests

url = "http://localhost:8080/welcome"
params = {
    "page": "1",
    "source": "desktop"
}

response = requests.get(url, params=params)

print("Servlet response:", response.text)
