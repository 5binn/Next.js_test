import type { Metadata } from "next";
// import { Inter } from "next/font/google";
// import "./globals.css";
import Link from "next/link";

// const inter = Inter({ subsets: ["latin"] });


export default function RootLayout({
  children,
}: Readonly<{
  children: React.ReactNode;
}>) {
  return (
    <html lang="ko">
      <body>
        <nav>
          <Link href={"/"}>|홈|</Link>
          <Link href={"/article"}>게시글|</Link>
          <Link href={"/user"}>사용자|</Link>
        </nav>
        {children}
      </body>
    </html>
  );
}
