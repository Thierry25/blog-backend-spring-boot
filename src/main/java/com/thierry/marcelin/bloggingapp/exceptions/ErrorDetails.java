package com.thierry.marcelin.bloggingapp.exceptions;

import java.time.LocalDateTime;

public record ErrorDetails(LocalDateTime time, String description, String details) {
}
