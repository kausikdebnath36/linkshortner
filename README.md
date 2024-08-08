  Api End Point
    1. POST /
        ◦ Request: { "originalUrl": "http://example.com" }
        ◦ Response:{ "shortUrl": "http://localhost:8080/abc123", "id": 1 }
        ◦ Functionality: Generates a unique short URL for the given destination URL and stores it in the database.
    2. POST /update
        ◦ Request: { "shortUrl": "http://localhost:8080/abc123", "originalUrl": "http://newexample.com" }
        ◦ Response: { "success": true }
        ◦ Functionality: Updates the destination URL for the given short URL.
    3. GET /{short_url}
        ◦ Response: { "destination_url": "http://example.com" }
        ◦ Functionality: Retrieves the original URL for the given short URL and redirects the user.
    4. POST /updateExpiry
        ◦ Request: { "short_url": "http://localhost:8080/abc123", "addOnDays": 30 }
        ◦ Response: { "success": true }
        ◦ Functionality: Updates the expiry date for the given short URL.
