package com.example.assignmentthree

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.constraintlayout.compose.Visibility


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RegistrationForm() {
    var name by remember { mutableStateOf(TextFieldValue("")) }
    var email by remember { mutableStateOf(TextFieldValue("")) }
    var studentId by remember { mutableStateOf(TextFieldValue("")) }
    var password by remember { mutableStateOf(TextFieldValue("")) }
    var isPasswordVisible by remember { mutableStateOf(false) }

    var nameError by remember { mutableStateOf(false) }
    var emailError by remember { mutableStateOf(false) }
    var studentIdError by remember { mutableStateOf(false) }
    var passwordError by remember { mutableStateOf(false) }
    var showEmptyFieldError by remember { mutableStateOf(false) }

    val context = LocalContext.current
    // for 59 batch
    val namePattern = "^[A-Za-z .]+$".toRegex()
    val emailPattern = "^cse_0182210012101[0-9]{3}@lus.ac.bd$".toRegex()
    val studentIdPattern = "^0182210012101[0-9]{3}$".toRegex()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        OutlinedTextField(
            value = name,
            onValueChange = {
                name = it
                nameError = !namePattern.matches(it.text)
            },
            label = { Text("Name") },
            isError = nameError || (name.text.isEmpty() && showEmptyFieldError),
            keyboardOptions = KeyboardOptions(imeAction = ImeAction.Next),
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp),
            colors = TextFieldDefaults.outlinedTextFieldColors(
                focusedBorderColor = Color(0xFF6200EE), 
                unfocusedBorderColor = Color.Gray
    
        )
        if (nameError) {
            Text("Not valid name format", color = MaterialTheme.colorScheme.error)
        }

        OutlinedTextField(
            value = email,
            onValueChange = {
                email = it
                emailError = !emailPattern.matches(it.text)
            },
            label = { Text("Email") },
            isError = emailError || (email.text.isEmpty() && showEmptyFieldError),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email, imeAction = ImeAction.Next),
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp),
            colors = TextFieldDefaults.outlinedTextFieldColors(
                focusedBorderColor = Color(0xFF6200EE),
                unfocusedBorderColor = Color.Gray
            )
        )
        if (emailError) {
            Text("Not valid email format", color = MaterialTheme.colorScheme.error)
        }

        OutlinedTextField(
            value = studentId,
            onValueChange = {
                studentId = it
                studentIdError = !studentIdPattern.matches(it.text)
            },
            label = { Text("Student ID") },
            isError = studentIdError || (studentId.text.isEmpty() && showEmptyFieldError),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number, imeAction = ImeAction.Next),
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp),
            colors = TextFieldDefaults.outlinedTextFieldColors(
                focusedBorderColor = Color(0xFF6200EE),
                unfocusedBorderColor = Color.Gray
            )
        )
        if (studentIdError) {
            Text("Not valid student ID format", color = MaterialTheme.colorScheme.error)
        }

        OutlinedTextField(
            value = password,
            onValueChange = {
                password = it
                passwordError = password.text.isEmpty()
            },
            label = { Text("Password") },
            isError = passwordError || (password.text.isEmpty() && showEmptyFieldError),
            visualTransformation = if (isPasswordVisible) VisualTransformation.None else PasswordVisualTransformation(),
            trailingIcon = {
                IconButton(onClick = { isPasswordVisible = !isPasswordVisible }) {
                    Icon(
                        painter = painterResource(id = if (isPasswordVisible) R.drawable.ic_visibility else R.drawable.ic_visibility_off),
                        contentDescription = if (isPasswordVisible) "Hide password" else "Show password"
                    )
                }
            },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password, imeAction = ImeAction.Done),
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp),
            colors = TextFieldDefaults.outlinedTextFieldColors(
                focusedBorderColor = Color(0xFF6200EE),
                unfocusedBorderColor = Color.Gray
            )
        )
        if (passwordError) {
            Text("Password cannot be empty", color = MaterialTheme.colorScheme.error)
        }

        Spacer(modifier = Modifier.height(16.dp))
        Button(
            onClick = {
                showEmptyFieldError = true
                if (!nameError && !emailError && !studentIdError && !passwordError && name.text.isNotEmpty() && email.text.isNotEmpty() && studentId.text.isNotEmpty() && password.text.isNotEmpty()) {
                     Toast.makeText(context, "Register", Toast.LENGTH_LONG).show()
                }
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp)
        ) {
            Text("Register")
        }
    }
}

fun showMessage(context: android.content.Context, message: String) {
    android.widget.Toast.makeText(context, message, android.widget.Toast.LENGTH_LONG).show()
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    RegistrationForm()
}
