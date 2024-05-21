package com.guru.ourfashionsapp.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.outlined.Favorite
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.guru.ourfashionsapp.R
import com.guru.ourfashionsapp.ui.theme.OurFashionsAppTheme


@Composable
fun FashionAppUI(navController: NavHostController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(16.dp)
    ) {
        AppBar()

        Spacer(modifier = Modifier.height(16.dp))

        SearchBar()

        Spacer(modifier = Modifier.height(16.dp))

        Promotions()

        Spacer(modifier = Modifier.height(16.dp))

        NewArrivals(navController)
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppBar() {
    TopAppBar(

        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = MaterialTheme.colorScheme.surface,
            titleContentColor = MaterialTheme.colorScheme.primary
        ),
        title = {},
        navigationIcon = {
            IconButton(onClick = { /* Handle navigation icon press */ }) {
                Image(
                    painter = painterResource(id = R.drawable.img_4), // Replace with your actual icon resource
                    contentDescription = "Navigation Icon",
                    modifier = Modifier.size(50.dp) // Increase the size as needed
                )
            }
        },
        actions = {
            IconButton(onClick = { /* Handle action icon press */ }) {
                Image(
                    painter = painterResource(id = R.drawable.img_5), // Replace with your actual icon resource
                    contentDescription = "Action Icon",
                    modifier = Modifier.size(50.dp) // Increase the size as needed
                )
            }
        }
    )
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchBar() {
    TextField(
        value = "",
        onValueChange = {},
        placeholder = { Text(text = "Search...") },
        modifier = Modifier
            .fillMaxWidth()
            .background(
                Color(0xFFF3F4F5),
                shape = RoundedCornerShape(50.dp)
            ) // Apply background color
            .padding(horizontal = 8.dp, vertical = 4.dp), // Padding to match the design
        singleLine = true,
        shape = RoundedCornerShape(50.dp), // Apply rounded shape to the TextField itself
        colors = TextFieldDefaults.textFieldColors(
            containerColor = Color.Transparent, // Make the TextField background transparent
            focusedIndicatorColor = Color.Transparent, // Remove the indicator line
            unfocusedIndicatorColor = Color.Transparent // Remove the indicator line
        ),
        leadingIcon = {
            Icon(
                imageVector = Icons.Default.Search,
                contentDescription = "Search icon"
            )
        }
    )
}


@Composable
fun Promotions() {
    LazyRow(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(8.dp), // Add spacing between items
    ) {
        items(promotions) { promotion ->
            PromotionCard(discount = promotion.discount, code = promotion.code)
        }
    }
}

// Sample data for promotions
val promotions = listOf(
    Promotion("50% Off", "TODAY50"),
    Promotion("70% Off", "TODAY70"),
    Promotion("50% Off", "TODAY50"),
    Promotion("70% Off", "TODAY70")

)

data class Promotion(val discount: String, val code: String)


@Composable
fun PromotionCard(discount: String, code: String) {
    Box(
        modifier = Modifier
            .width(270.dp)
            .padding(8.dp)
            .background(Color.LightGray, RoundedCornerShape(8.dp))
            .clickable { }
    ) {
        // Top section with discount text and optional image
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(140.dp),  // Adjust height as needed
            horizontalArrangement = Arrangement.End,
        ) {
            // Add an image here if needed (replace with your image resource)
            Text(
                text = discount,
                fontSize = 24.sp, // Increased font size for better visibility
                fontWeight = FontWeight.Bold,
                color = Color.Black, // White text for better contrast
                modifier = Modifier.padding(8.dp),
                textAlign = TextAlign.Center
            )
            Image(
                painter = painterResource(id = R.drawable.bag),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .padding(start = 60.dp)
                    .fillMaxWidth()
                    .fillMaxHeight()

            )
        }

        Column(
            modifier = Modifier
                .padding(top = 24.dp)
                .padding(8.dp),
        ) {
            Text(
                text = "On everything today",
                fontSize = 18.sp,
                textAlign = TextAlign.Center,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(top = 8.dp)
            )
            Text(
                text = "With code: $code",
                fontSize = 14.sp,
                modifier = Modifier.padding(bottom = 8.dp, top = 8.dp)
            )
            Button(
                onClick = { },
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color.Black,
                    contentColor = Color.White
                ), modifier = Modifier
                    .size(105.dp, 37.dp)

            ) {
                Text(text = "Get Now")
            }
        }
    }
}

@Composable
fun NewArrivals(navController: NavHostController) {
    Column(modifier = Modifier.padding(16.dp)) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 8.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(text = "New Arrivals", fontWeight = FontWeight.Bold, fontSize = 20.sp)
            Text(
                text = "View All",
                color = Color.Blue,
                modifier = Modifier.clickable { }
            )
        }

        val screenWidth = LocalConfiguration.current.screenWidthDp
        val isTablet = screenWidth >= 600

        val gridColumns = if (isTablet) 3 else 2

        LazyVerticalGrid(
            columns = GridCells.Fixed(gridColumns),
            contentPadding = PaddingValues(horizontal = 8.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            val names = listOf(
                "Glamour Goddess",
                "Enchanting Evening",
                "Radiant Ruffles",
                "Femme Fatale Maxi",
                "Sophisticated Sheath",
                "Chic Cocktail",
                "Glamour Goddess",
                "Enchanting Evening",
                "Radiant Ruffles",
                "Femme Fatale Maxi",
                "Sophisticated Sheath",
                "Chic Cocktail"
            )

            val prices = listOf(
                "$250.00",
                "$400.00",
                "$180.00",
                "$300.00",
                "$220.00",
                "$150.00",
                "$250.00",
                "$400.00",
                "$180.00",
                "$300.00",
                "$220.00",
                "$150.00"
            )

            val descriptions = listOf(
                "Sparkling Sequin",
                "Elegant Lace",
                "Flowing Chiffon",
                "Sleek Satin Maxi",
                "Tailored Crepe",
                "Flirty Tulle",
                "Sparkling Sequin",
                "Elegant Lace",
                "Flowing Chiffon",
                "Sleek Satin Maxi",
                "Tailored Crepe",
                "Flirty Tulle"
            )

            val imageResourceIds = listOf(
                R.drawable.img_06,
                R.drawable.img_01,
                R.drawable.img_02,
                R.drawable.img_03,
                R.drawable.img_04,
                R.drawable.img_01,
                R.drawable.img_06,
                R.drawable.img_01,
                R.drawable.img_02,
                R.drawable.img_03,
                R.drawable.img_04,
                R.drawable.img_01
            )

            items(names.indices.toList()) { index ->
                val id = index // Use the index as the ID
                val name = names[index]
                val price = prices[index]
                val description = descriptions[index]
                val imageResourceId = imageResourceIds[index]

                NewArrivalItem(
                    id = id, // Pass the ID to NewArrivalItem
                    name = name,
                    price = price,
                    imageResourceId = imageResourceId,
                    des = description,
                    onFavoriteClick = {},
                    onItemClicked = { itemId, itemName, itemPrice, itemDescription, itemImageResourceId ->
                        // Navigate to ProductDetailScreen while passing the required data
                        navController.navigate("productDetailScreen/$itemId/$itemName/$itemPrice/$itemDescription/$itemImageResourceId")
                    }
                )
            }
        }
    }
}

@Composable
fun NewArrivalItem(
    id: Int, // Include an ID parameter
    name: String,
    price: String,
    des: String,
    imageResourceId: Int,
    onFavoriteClick: () -> Unit,
    onItemClicked: (Int, String, String, String, Int) -> Unit
) {
    Box(
        modifier = Modifier
            .padding(8.dp)
            .background(Color.White, RoundedCornerShape(8.dp))
            .clickable {
                onItemClicked(id, name, price, des, imageResourceId)
            },
        contentAlignment = Alignment.TopEnd
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Image(
                painter = painterResource(id = imageResourceId),
                contentDescription = name,
                contentScale = ContentScale.Fit,
                modifier = Modifier
                    .size(200.dp)
                    .clip(RoundedCornerShape(8.dp))
            )

            Text(
                text = name,
                fontSize = 14.sp,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center,
                modifier = Modifier
            )

            Text(
                text = des,
                fontSize = 12.sp,
                textAlign = TextAlign.Center,
                modifier = Modifier

            )

            Text(
                text = price,
                fontSize = 14.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black,
                textAlign = TextAlign.Center,
                modifier = Modifier
            )
        }

        IconButton(
            onClick = onFavoriteClick,
            modifier = Modifier
                .padding(end = 8.dp, top = 8.dp)
                .align(Alignment.TopEnd)
        ) {
            Icon(
                imageVector = Icons.Outlined.Favorite,
                contentDescription = null,
            )
        }
    }
}


@Preview(name = "tablet", device = "spec:shape=Normal,width=1280,height=800,unit=dp,dpi=480")
@Preview(showBackground = true)
@Composable
fun DefaultPreview1() {
    val navController = rememberNavController()
    OurFashionsAppTheme {
        FashionAppUI(navController)
    }
}
