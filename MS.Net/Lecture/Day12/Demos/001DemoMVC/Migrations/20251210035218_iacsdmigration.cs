using Microsoft.EntityFrameworkCore.Migrations;

#nullable disable

namespace _001DemoMVC.Migrations
{
    /// <inheritdoc />
    public partial class iacsdmigration : Migration
    {
        /// <inheritdoc />
        protected override void Up(MigrationBuilder migrationBuilder)
        {
            migrationBuilder.CreateTable(
                name: "Employee",
                columns: table => new
                {
                    No = table.Column<int>(type: "int", nullable: false)
                        .Annotation("SqlServer:Identity", "1, 1"),
                    Name = table.Column<string>(type: "varchar(50)", maxLength: 50, nullable: false),
                    Address = table.Column<string>(type: "varchar(50)", maxLength: 50, nullable: false)
                },
                constraints: table =>
                {
                    table.PrimaryKey("PK_Employee", x => x.No);
                });

            migrationBuilder.CreateTable(
                name: "Subject",
                columns: table => new
                {
                    SID = table.Column<int>(type: "int", nullable: false)
                        .Annotation("SqlServer:Identity", "1, 1"),
                    SName = table.Column<string>(type: "varchar(50)", maxLength: 50, nullable: false)
                },
                constraints: table =>
                {
                    table.PrimaryKey("PK_Subject", x => x.SID);
                });

            migrationBuilder.CreateTable(
                name: "Trainer",
                columns: table => new
                {
                    TID = table.Column<int>(type: "int", nullable: false)
                        .Annotation("SqlServer:Identity", "1, 1"),
                    TName = table.Column<string>(type: "varchar(50)", maxLength: 50, nullable: false)
                },
                constraints: table =>
                {
                    table.PrimaryKey("PK_Trainer", x => x.TID);
                });

            migrationBuilder.CreateTable(
                name: "SubjectTrainer",
                columns: table => new
                {
                    SubjectsSubjectID = table.Column<int>(type: "int", nullable: false),
                    TrainersTrainerID = table.Column<int>(type: "int", nullable: false)
                },
                constraints: table =>
                {
                    table.PrimaryKey("PK_SubjectTrainer", x => new { x.SubjectsSubjectID, x.TrainersTrainerID });
                    table.ForeignKey(
                        name: "FK_SubjectTrainer_Subject_SubjectsSubjectID",
                        column: x => x.SubjectsSubjectID,
                        principalTable: "Subject",
                        principalColumn: "SID",
                        onDelete: ReferentialAction.Cascade);
                    table.ForeignKey(
                        name: "FK_SubjectTrainer_Trainer_TrainersTrainerID",
                        column: x => x.TrainersTrainerID,
                        principalTable: "Trainer",
                        principalColumn: "TID",
                        onDelete: ReferentialAction.Cascade);
                });

            migrationBuilder.CreateIndex(
                name: "IX_SubjectTrainer_TrainersTrainerID",
                table: "SubjectTrainer",
                column: "TrainersTrainerID");
        }

        /// <inheritdoc />
        protected override void Down(MigrationBuilder migrationBuilder)
        {
            migrationBuilder.DropTable(
                name: "Employee");

            migrationBuilder.DropTable(
                name: "SubjectTrainer");

            migrationBuilder.DropTable(
                name: "Subject");

            migrationBuilder.DropTable(
                name: "Trainer");
        }
    }
}
